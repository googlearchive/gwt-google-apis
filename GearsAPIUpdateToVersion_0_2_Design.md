# Goals #
  * Expose the new features of the Gears API version 0.2 in a GWT friendly way.

# Non-goals #
  * TBD

# Changes Gears API version 0.2 #
  1. New HttpRequest Module API
    * This only makes sense for workers.  Most likely we would make GWT's RequestBuilder use the Gears XmlHttpRequest object.
  1. New Timer Module API
    * This only makes sense for workers.  Most likely we would make GWT's Timer class rebind to use the Gears internal timer.
  1. Update WorkerPool API
    * Add onerror callback - this can only be set from the worker; does not impact the parent thread
    * Add createWorkerFromURL -
    * Add allowCrossOrigin - this can only be called from the worker
    * Update onmessage callback to use the messageObject parameter
  1. Update LocalServer API
  1. Expose the Factory version property

# Proposed Use Cases #
  1. Declare a worker thread.
  1. Create a worker thread from a URL.
  1. Allow HttpRequest Module API to be used from a worker thread.
  1. Allow the Timer Module API to be used from a worker thread.

# Open Issues #
  1. Will WorkerPool.createWorkerFromUrl allow a worker thread to be created from the {GWT Module}.nocache.js file?
  1. Can we use a GWT rebind rule to select the Gears HttpRequest Module API when GWT's RequestBuilder is used from within a worker thread module?
  1. Can we use a GWT rebind rule to select the Gears Timer Module API when GWT's Timer class is used from a worker thread?