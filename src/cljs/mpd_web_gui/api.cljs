(ns mpd-web-gui.api
  (:require [ajax.core :as ajax]))

(defn make-request
  ([action handler] (make-request action {} handler))
  ([action params handler]
   (ajax/POST "http://192.168.0.14:6789"
              {:response-format (ajax/json-response-format {:keywords? true})
               :format (ajax/json-request-format)
               :params  (assoc params :action action)
               :handler handler})))

(defn log-response [response-map]
  (js/console.log (str response-map)))

(defn status [handler]
  (make-request "status" handler))

(defn play-next []
  (make-request "next" log-response))

(defn play-previous []
  (make-request "previous" log-response))

(defn pause []
  (make-request "pause" log-response))

(defn play
  ([] (play nil))
  ([position]
   (make-request "play" {:position position} log-response)))
