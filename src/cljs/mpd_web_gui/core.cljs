(ns mpd-web-gui.core
  (:require [mpd-web-gui.api :as api]
            [rum.core :as rum]))

(enable-console-print!)

(defonce app-state (atom {}))

(defn update-status []
  (api/status
   (fn [data]
     (swap! app-state #(assoc % :status data)))))

(defonce update-status-interval-id
  (js/setInterval #(update-status) 2000))
