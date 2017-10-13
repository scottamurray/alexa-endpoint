; For more on Alexa response cards, see http://amzn.to/2yfq2I7.

(ns alexa.cards)

(defn simple
  "Returns a Simple response card with the given title and plaintext content."
  [title content]
  {:type "Simple"
   :title title
   :content content})

(defn standard
  "Returns a Standard response card with the given title and text."
  [title text]
  {:type "Standard"
   :title title
   :text text})

(defn with-image
  "Returns a Standard response card with the given images."
  [standard-card small-image-url large-image-url]
  (assoc standard-card :image {:small-image-url small-image-url
                               :large-image-url large-image-url}))

(defn link-account
  "Returns a new LinkAccount response card."
  []
  {:type "LinkAccount"})
