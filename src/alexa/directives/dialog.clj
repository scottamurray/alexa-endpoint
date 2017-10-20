; For more on Alexa response directives, see http://amzn.to/2xxIzMa.

(ns alexa.directives.dialog)

(defn delegate
  "Returns a directive telling Alexa to to handle the next turn in
  the dialog with the user. For more, see http://amzn.to/2xxZ8qY."
  []
  {:type "Dialog.Delegate"})

(defn elicit-slot
  "Returns a directive telling Alexa to to ask the user for the value of
  a specific slot. For more, see http://amzn.to/2fYF4Ys."
  [slot-name]
  {:type "Dialog.ElicitSlot"
   :slot-to-elicit slot-name})

(defn confirm-slot
  "Returns a directive telling Alexa to confirm the value of a specific slot
  before continuing with the dialog. For more, see http://amzn.to/2yYzvRu."
  [slot-name]
  {:type "Dialog.ConfirmSlot"
   :slot-to-confirm slot-name})

(defn confirm-intent
  "Returns a directive telling Alexa to confirm all the information the user
  has provided for the intent before the skill takes action. For more,
  see http://amzn.to/2yeIgJR."
  []
  {:type "Dialog.ConfirmIntent"})

(defn with-updated-intent
  "Returns the given directive with an updated intent."
  [directive updated-intent]
  (assoc directive :updated-intent updated-intent))
