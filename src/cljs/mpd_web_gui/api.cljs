(ns mpd-web-gui.api
  (:require [ajax.core :as ajax]))

(defn make-request
  ([action handler] (make-request action {} handler))
  ([action params handler]
   (ajax/POST "http://localhost:6789"
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

(defn current-playlist [handler]
  (make-request "queue" handler))

(defn files [handler]
  (make-request "files" handler))

(defn add-to-queue [files]
  (make-request "add_to_queue" {:files files} log-response))

(defn clear-queue []
  (make-request "clear_queue" log-response))

(defn set-volume [v]
  (make-request "set_volume" {:value v} log-response))

(defn playlists [handler]
  (make-request "playlists" handler))

(defn load-playlist [name]
  (make-request "load_playlist" {:name name} log-response))

(defn save-playlist [name handler]
  (make-request "save_playlist" {:name name} handler))

(defn delete-playlist [name handler]
  (make-request "delete_playlist" {:name name} handler))
