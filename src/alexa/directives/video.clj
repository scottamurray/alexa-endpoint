; For more on Alexa response directives, see http://amzn.to/2xxIzMa.

(ns alexa.directives.video)

(defn launch
  "Returns a directive telling Alexa to open its video app and stream video
  from the given URL. For more, see http://amzn.to/2yfymY2."
  [video-url title subtitle]
  {:type "VideoApp.Launch"
   :video-item {:source video-url
                :metadata {:title title
                           :subtitle subtitle}}})
