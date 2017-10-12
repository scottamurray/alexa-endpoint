; For more on Alexa response directives, see http://amzn.to/2xxIzMa.

(ns alexa.directives.display)

(defn render-template
  "Returns a directive telling Alexa to render content to the app screen.
  For more, see http://amzn.to/2gaqmBu."
  []
  {:type "Display.RenderTemplate"})

(defn using-full-width-text-template
  "Applies the full-width text template to the given RenderTemplate directive."
  [directive token title text]
  (assoc directive :template {:type "BodyTemplate1"
                              :token token
                              :title title
                              :text-content text}))

(defn using-image-left-template [] {})

(defn using-image-right-template [] {})

(defn using-full-screen-image-overlay-template [] {})

(defn using-vertical-list-template [] {})

(defn using-horizontal-list-with-image-template [] {})
