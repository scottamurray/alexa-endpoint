; For more on Alexa's request format, see http://amzn.to/2fVsObs.

(ns alexa.request
  (:require [camel-snake-kebab.core :refer :all]
            [clojure.data.json :as json]))

(defn from-json
  "Unserializes a raw Alexa request."
  [request-json]
  (json/read-str request-json :key-fn ->kebab-case-keyword))

(defn id
  "Returns a request's unique identifier."
  [request]
  (request :request-id))

(defn type
  "Returns a request's type."
  [request]
  (request :type))

(defn timestamp
  "Returns a request's timestamp."
  [request]
  (request :timestamp))

(defn- dialog-state
  "Returns the dialog state of an IntentRequest. For more on IntentRequest
  dialog states, see http://amzn.to/2yAYnl7."
  [request]
  (request :dialog-state))

(defn started?
  "Returns true if the given IntentRequest has started."
  [request]
  (= (dialog-state request) "STARTED"))

(defn in-progress?
  "Returns true if the given IntentRequest is in progress."
  [request]
  (= (dialog-state request) "IN_PROGRESS"))

(defn completed?
  "Returns true if the given IntentRequest has completed."
  [request]
  (= (dialog-state request) "COMPLETED"))
