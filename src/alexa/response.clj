; For more on Alexa's response format, see http://amzn.to/2hZ4tpa.

(ns alexa.response
  (:require [clojure.string :as str]
            [clojure.data.json :as json]))

(defn response
  "Returns an empty Alexa response."
  []
  {:output-speech {:type nil
                   :text nil}
   :reprompt {:output-speech {:type nil
                              :text nil}}
   :should-end-session true})

(defn output-speech
  "Sets a response's output speech text."
  [response speech-text]
  (-> response
      (assoc-in [:output-speech :type] "PlainText")
      (assoc-in [:output-speech :text] speech-text)))

(defn reprompt-speech
  "Sets a response's reprompt speech text."
  [response speech-text]
  (-> response
      (assoc-in [:reprompt :output-speech :type] "PlainText")
      (assoc-in [:reprompt :output-speech :text] speech-text)))

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

(defn- output-speech-removed
  "Returns a response with output speech removed. Delegated responses cannot
  have output speech or reprompt properties."
  [response]
  (-> response
      (dissoc :output-speech)
      (dissoc :reprompt)))

(defn- with-directive
  "Returns a response with the given directive added."
  [response directive]
  (let [directives (response :directives)]
    (assoc response :directives (conj directives directive))))

(defn delegate
  "Marks a response as one that should delegate with the given updated intent.
  For more on delegating responses in multi-turn conversations,
  see http://amzn.to/2xvhCZg. Delegated responses cannot have output speech
  or reprompt text properties, so this function removes them."
  [response updated-intent]
  (let [delegate-directive {:type "Dialog.Delegate"
                            :updated-intent updated-intent}]
    (-> response
        (output-speech-removed)
        (with-directive delegate-directive))))

(defn- keyword-to-camel-case
  "Converts a keyword to a camel case string for use in JSON."
  [keyword]
  (let [keyword-str (name keyword)
        find-regex #"-(\w)"
        replace-fn #(str/upper-case (second %1))]
    (str/replace keyword-str regex replace-fn)))

(defn serialize
  "Serializes an Alexa response for transfer over HTTPS."
  [response]
  (let [wrapped-response {:version "1.0"
                          :response response}]
    (json/write-str wrapped-response :key-fn keyword-to-camel-case)))
