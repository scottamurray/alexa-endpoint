; For more on Alexa response directives, see http://amzn.to/2xxIzMa.

(ns alexa.directives.display)

(defn render-template
  "Returns a directive telling Alexa to render content to the app screen.
  For more, see http://amzn.to/2gaqmBu."
  [token title]
  {:type "Display.RenderTemplate"
   :template {:token token
              :title title}})

(defn using-full-width-text-template
  "Applies the full-width text template to the given RenderTemplate directive."
  [directive]
  (assoc directive :template :type "BodyTemplate1"))

(defn using-image-left-template
  "Applies the image left template to the given RenderTemplate directive."
  [directive]
  (-> directive
      (assoc-in [:template :type] "BodyTemplate3")
      (assoc-in [:template :image :sources] [])))

(defn using-image-right-template
  "Applies the image left template to the given RenderTemplate directive."
  [directive]
  (-> directive
      (assoc-in [:template :type] "BodyTemplate2")
      (assoc-in [:template :image :sources] [])))

(defn using-full-screen-image-overlay-template
  "Applies the full-screen image with text overlay template to the given
  RenderTemplate directive."
  [directive]
  (-> directive
      (assoc-in [:template :type] "BodyTemplate6")
      (assoc-in [:template :image :sources] [])))

(defn using-vertical-list-template [] {})

(defn using-horizontal-list-with-image-template [] {})

(defn- with-text-content
  "Applies text content to a given RenderTemplate directive."
  [directive type primary secondary tertiary]
  (let [text-content {:primary-text {:type type
                                     :text primary}
                      :secondary-text {:type type
                                       :text secondary}
                      :tertiary-text  {:type type
                                       :text tertiary}}]
    (assoc-in directive [:template :text-content] text-content)))

(defn with-plain-text-content
  "Applies plaintext content to a given RenderTemplate directive."
  [directive primary secondary tertiary]
  (with-text-content directive "PlainText" primary secondary tertiary))

(defn with-rich-text-content
  "Applies plaintext content to a given RenderTemplate directive."
  [directive primary secondary tertiary]
  (with-text-content directive "RichText" primary secondary tertiary))

(defn with-background-image
  "Applies a background image to a given RenderTemplate directive."
  [directive image-url width height]
  (assoc-in directive [:template :background-image] {:url image-url
                                                     :width-pixels width
                                                     :height-pixels height}))

(defn with-single-image
  "Applies an image to the given RenderTemplate directives. Single images can
  only be applied to directives using BodyTemplate2 or BodyTemplate3."
  [directive image-url width height]
  (let [sources (get-in directive [:template :image :sources])
        image {:url image-url
               :width-pixels width
               :height-pixels height}]
    (assoc-in directive [:template :image :sources]
      (conj sources image))))

(defn hidden-back-button
  "Returns a RenderTemplate directive with the back button hidden."
  [directive]
  (assoc directive :back-button "HIDDEN"))
