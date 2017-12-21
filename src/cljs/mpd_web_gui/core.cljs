(ns mpd-web-gui.core
  (:require [mpd-web-gui.api :as api]
            [rum.core :as rum]))

(enable-console-print!)

(defonce app-state (atom {}))
(defonce playlist (atom []))
(defonce current-playlist-filter (atom ""))

(defn update-status []
  (api/status
   (fn [data]
     (swap! app-state #(assoc % :status data)))))

(defn update-current-playlist []
  (api/current-playlist #(reset! playlist %)))

(defonce update-status-interval-id
  (js/setInterval #(update-status) 2000))

(defonce update-current-playlist-interval-id
  (js/setInterval #(update-current-playlist) 5000))

(defn wrap-state
  "For wrapping rum middlewares that do not update state"
  [f]
  (fn [state]
    (f)
    state))
