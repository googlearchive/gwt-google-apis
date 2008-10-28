/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

/* 
 * pi_worker.js - calculate digits of Pi, Gears WorkerPool implementation
 * 
 * Pi Spigot algorithm implementation in JavaScript
 * 
 * Adapted from: "A Spigot Algorithm for the Digits of Pi" by Stanley Rabinowitz
 * and Stan Wagon http://www.mathpropress.com/stan/bibliography/spigot.pdf
 * 
 * This algorithm doesn't require the use of an arbitrary precision Math
 * library, (or even any floating point!) but does require quite a bit of memory
 * (an array of integers about 3.5 x the number of digits to calculate long.)
 */

var wp = google.gears.workerPool;

wp.onmessage = function(a, b, message) {
  // START message is followed by the number of digits to compute.
  if (message.body.substr(0,5) == "START") {
    var numDigits = Number(message.body.substr(6));
    wp.sendMessage("MSG Computing " + numDigits + " digits.", message.sender);
  	startPiSpigot(message.sender, numDigits);
  }
}

function startPiSpigot(senderId, numDigits) {
    var cb = function (digits) {
		wp.sendMessage("DIGITS " + digits, senderId);
    };
	var piSpigot = new PiSpigot(numDigits, 100, cb);
	piSpigot.compute();
	// send a message to indicate the end of the computation.
	wp.sendMessage("END", senderId);
}

// Constructor for a PiSpigot object.  
// numDigits - Number - the total number of digits to computer
// messageLen - Number - when the number of digits computed reaches this length, spit it out
// messageCb - function(digitString) - when the number of digits reaches messageLen, 
//      call this function with the results
function PiSpigot(numDigits, messageLen, messageCb) {
	this.n = numDigits + 1;
	this.messageCb = messageCb;
	this.digitBufferLen = messageLen;
	this.digitBuffer = "";
	this.numDigitsBuffered = 0;
	this.storageLen = Math.floor((this.n * 10) / 3);
	this.storage = new Array(this.storageLen + 1);
	this.wholePartDropped = false;
	this.stopFlag = false;
}

PiSpigot.prototype.flushDigitBuffer = function () {
  	this.messageCb(this.digitBuffer);
  	this.digitBuffer = "";
}

PiSpigot.prototype.bufferDigit = function (digit) {
  // assert (digit < 10);
  // assert (digit >= 0);
  this.numDigitsBuffered++;
  if (!this.wholePartDropped) {
  	if (this.numDigitsBuffered == 2) {
  	  this.wholePartDropped = true;
  	  this.numDigitsBuffered = 0;
  	}
    return;
  }  
  this.digitBuffer += digit;
    if (this.numDigitsBuffered % this.digitBufferLen == 0) {
  	this.flushDigitBuffer();
  }
}

PiSpigot.prototype.compute = function() {
  var i, j, k, q, x;
  var nines = 0;
  var predigit = 0;
	
  // Start with a table of all 2's
  for (j = 1; j <= this.storageLen; j++) {
    this.storage[j] = 2;
  }
  // First predigit is a 0
  for (j = 1; j <= this.n; j++) {
    for (q = 0, i = this.storageLen; i > 0; i--) {
      x = 10 * this.storage[i] + q * i;
      this.storage[i] = x % (2 * i - 1);
      q = Math.floor(x / (2 * i - 1));
    } // for q
      
    this.storage[1] = q % 10;
    q = Math.floor(q / 10);

    if (q == 9) {
      nines++;
    } else if (q == 10) {
      this.bufferDigit(predigit + 1);
      for (k = 1; k <= nines; k++) {
        this.bufferDigit(0);
      }
      predigit = 0;
      nines = 0;
    } else {
      this.bufferDigit(predigit);
      predigit = q;
      if (nines != 0) {
        for (k = 1; k <= nines; k++) { 
           this.bufferDigit(9);
        }
        nines = 0;
      }
    }
  } // for j
  this.bufferDigit(predigit);
  this.flushDigitBuffer();
}