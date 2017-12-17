(ns mpd-web-gui.core
  (:require [mpd-web-gui.api :as api]
            [rum.core :as rum]))

(enable-console-print!)

(defonce app-state (atom {}))

(defn update-status []
  (api/status
   (fn [data]
     (swap! app-state #(assoc % :status data)))))

(defn update-current-playlist []
  (api/current-playlist
   (fn [data]
     (swap! app-state #(assoc % :current-playlist data)))))

(defonce update-status-interval-id
  (js/setInterval #(update-status) 2000))

(defonce update-current-playlist-interval-id
  (js/setInterval #(update-current-playlist) 5000))
