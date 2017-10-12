; For more on Alexa response directives, see http://amzn.to/2xxIzMa.

(ns alexa.directives.audio)

(defn play
  "Returns a directive telling Alexa to stream audio from a given URL.
  For more, see http://amzn.to/2yf5TC4."
  [token audio-url play-behavior]
  {:type "AudioPlayer.Play"
   :play-behavior play-behavior
   :audio-item {:stream {:url audio-url
                         :token token}}})

(defn stop
  "Returns a directive telling Alexa to stop playing audio. For more,
  see http://amzn.to/2xxvirB."
  []
  {:type "AudioPlayer.Stop"})

(defn clear-queue
   "Returns a directive telling Alexa to clear all audio from the queue.
   For more, see http://amzn.to/2y7zJJc."
   [clear-behavior]
   {:type "AudioPlayer.ClearQueue"
    :clear-behavior clear-behavior})
