; For more on Alexa request intents, see http://amzn.to/2xSHp2I.

(ns alexa.intents)

(defn intent
  "Returns an Alexa request's intent."
  [request]
  (request :intent))

(defn name
  "Returns the name of an intent."
  [intent]
  (intent :name))

(defn confirmation-status
  "Returns an intent's confirmation status. For more on intent confirmation
  statuses, see http://amzn.to/2yU1QIN."
  [intent]
  (intent :confirmation-status))

(defn slot-value
  "Returns the value of an intent slot."
  [intent slot-name-keyword]
  (let [slots (intent :slots)]
    (get-in slots [slot-name-keyword :value])))

(defn slot-confirmation-status
  "Returns the confirmation status of an intent slot."
  [intent slot-name-keyword]
  (let [slots (intent :slots)]
    (get-in slots [slot-name-keyword :confirmation-status])))
