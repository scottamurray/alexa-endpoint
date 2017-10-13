; For more on Alexa's response format, see http://amzn.to/2hZ4tpa.

(ns alexa.response
  (:require [camel-snake-kebab.core :refer :all]
            [clojure.data.json :as json]))

(defn response
  "Returns an empty Alexa response."
  []
  {:output-speech {:type "PlainText"
                   :text ""}
   :reprompt {:output-speech {:type "Plaintext"
                              :text ""}}
   :should-end-session true})

(defn output-speech
  "Sets a response's output speech text."
  [response speech-text]
  (assoc-in response [:output-speech :text] speech-text}))

(defn reprompt-speech
  "Sets a response's reprompt speech text."
  [response speech-text]
  (assoc-in response [:reprompt :output-speech :text] speech-text}))

(defn- should-end-session
  "Marks whether a response should end the session."
  [response should-end-session?]
  (assoc response :should-end-session should-end-session?))

(defn end-session
  "Marks a response as one that should end the session."
  [response]
  (should-end-session response true))

(defn keep-session-alive
  "Marks a response as one that should keep the session alive."
  [response]
  (should-end-session response false))

(defn with-directive
  "Returns a response with the given directive added."
  [response directive]
  (let [directives (response :directives)]
    (assoc response :directives (conj directives directive))))

(defn with-card
  "Returns a response with the given card added. Cards may only be included in
  responses to a LaunchRequest or IntentRequest."
  [response card]
  (assoc response :card card))

(defn serialize
  "Serializes an Alexa response for transfer over HTTPS."
  [response]
  (let [wrapped-response {:version "1.0"
                          :response response}]
    (json/write-str wrapped-response :key-fn ->camelCaseString)))
