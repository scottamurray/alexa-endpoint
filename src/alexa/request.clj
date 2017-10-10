; For more on Alexa's request format, see http://amzn.to/2fVsObs.

(ns alexa.request
  (:require [clojure.data.json :as json]))

(defn from-json
  "Unserializes a raw Alexa request."
  [request-json]
  (json/read-str request-json :key-fn keyword))
