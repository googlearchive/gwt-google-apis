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
package com.google.gwt.gears.sample.workerpooldemo.client;

/**
 * Calculate digits of Pi.
 * 
 * Adapted from: "A Spigot Algorithm for the Digits of Pi" by Stanley Rabinowitz
 * and Stan Wagon http://www.mathpropress.com/stan/bibliography/spigot.pdf
 * 
 * This algorithm doesn't require the use of an arbitrary precision Math
 * library, (or even any floating point!) but does require quite a bit of memory
 * (an array of integers about 3.5 x the number of digits to calculate long.)
 * 
 */
public class PiSpigot {

  /**
   * A method to invoke when the digit buffer is full to
   * output digits back to the caller.
   */
  public interface Callback {
    void onDigits(String digitString);
  }

  /**
   * Temporary storage for computed digits.
   */
  private StringBuilder digitBuffer = new StringBuilder();
  
  /**
   * Number of digits to buffer in digitBuffer before formatting.
   */
  private final int digitBufferSize;

  /**
   * Number of digits to calculate.
   */
  private final int n;
  
  /**
   * Number of digits currently buffered in formattedDigitBuffer.
   */
  private int numDigitsBuffered = 0;

  /**
   * Storage for the computation.
   */
  private int[] storage;

  /**
   * length of the storage array needed for n. 3 1/3 times the number of digits
   * to compute.
   */
  private final int storageLen;

  /**
   * Used to shave off the first 2 digits of the calculation when printing.
   */
  private boolean wholePartDropped = false;

  /**
   * Callback method to invoke when the digitBuffer is full.
   */
  private Callback cb;
  
  /**
   * Create a new Pi digit computation.
   * 
   * @param numDigits number of digits to compute
   * @param bufferSize number of digits to print per line
   * @param cb callback to invoke when the digit buffer is full
   */
  public PiSpigot(int numDigits, int bufferSize, Callback cb) {
    this.n = numDigits + 1;
    this.storageLen = (10 * n) / 3;
    this.storage = new int[storageLen + 1];
    this.digitBufferSize = bufferSize;
    this.cb = cb;
  }

  public void compute() {
    int i, j, k;
    int q, x;
    int nines = 0;
    int predigit = 0;

    // Start with a table of all 2's
    for (j = 1; j <= storageLen; j++) {
      storage[j] = 2;
    }

    // First predigit is a 0
    for (j = 1; j <= n; j++) {
      for (q = 0, i = storageLen; i > 0; i--) {
        x = 10 * storage[i] + q * i;
        storage[i] = x % (2 * i - 1);
        q = x / (2 * i - 1);
      } // for q

      storage[1] = q % 10;
      q /= 10;

      if (q == 9) {
        nines++;
      } else if (q == 10) {
        bufferDigit(predigit + 1);
        for (k = 1; k <= nines; k++) {
          bufferDigit(0);
        }
        predigit = 0;
        nines = 0;
      } else {
        bufferDigit(predigit);
        predigit = q;
        if (nines != 0) {
          for (k = 1; k <= nines; k++) {
            bufferDigit(9);
          }
          nines = 0;
        }
      }
    } // for j
    bufferDigit(predigit);
    flushDigitBuffer();
  } // end compute()

  private void bufferDigit(int digit) {
    assert (digit < 10);
    assert (digit >= 0);
    numDigitsBuffered++;
    // (Drop the first 2 digits, they are "03")
    if (!wholePartDropped) {
      if (numDigitsBuffered == 2) {
        wholePartDropped = true;
        numDigitsBuffered = 0;
      }
      return;
    }
    digitBuffer.append(Integer.toString(digit));
    if (digitBuffer.length() == digitBufferSize) {
      flushDigitBuffer();
    }
  };

  /**
   * Print the last digits left in the buffer.
   */
  private void flushDigitBuffer() {
    if (digitBuffer.length() <= 0) {
      return;
    }
    cb.onDigits(digitBuffer.toString());
    digitBuffer = new StringBuilder();
  };
}
