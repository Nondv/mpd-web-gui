(ns mpd-web-gui.api
  (:require [ajax.core :as ajax]))

(defn status [handler]
  (ajax/POST "http://192.168.0.14:6789"
             {:response-format (ajax/json-response-format {:keywords? true})
              :format (ajax/json-request-format)
              :params  {:action "status"}
              :handler handler}))

(defn log-response [response-map]
  (js/console.log (str response-map)))

(defn play-next []
  (ajax/POST "http://192.168.0.14:6789"
             {:response-format (ajax/json-response-format {:keywords? true})
              :format (ajax/json-request-format)
              :params  {:action "next"}
              :handler log-response}))

(defn play-previous []
  (ajax/POST "http://192.168.0.14:6789"
             {:response-format (ajax/json-response-format {:keywords? true})
              :format (ajax/json-request-format)
              :params  {:action "previous"}
              :handler log-response}))

(defn pause []
  (ajax/POST "http://192.168.0.14:6789"
             {:response-format (ajax/json-response-format {:keywords? true})
              :format (ajax/json-request-format)
              :params  {:action "pause"}
              :handler log-response}))

(defn play
  ([] (play nil))
  ([position]
   (ajax/POST "http://192.168.0.14:6789"
              {:response-format (ajax/json-response-format {:keywords? true})
               :format (ajax/json-request-format)
               :params  {:action "play" :position position}
               :handler log-response})))
