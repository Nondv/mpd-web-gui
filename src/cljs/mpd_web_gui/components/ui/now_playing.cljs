(ns mpd-web-gui.components.ui.now-playing
  (:require [rum.core :as rum]
            [mpd-web-gui.core :refer [app-state]]))


(defn current-song [state]
  (get-in state [:status :currentSong]))

(defn title [state]
  (:title (current-song state)))

(defn artist [state]
  (:artist (current-song state)))

(defn album [state]
  (:album (current-song state)))

(defn current-song-label [state]
  (str (artist state) " - " (title state)))

(rum/defc now-playing < rum/reactive []
  [:div
   [:div [:strong (current-song-label (rum/react app-state))]]
   [:div {:class "text-secondary"} [:small (album (rum/react app-state))]]])
