(ns mpd-web-gui.components.ui.current-playlist
  (:require [rum.core :as rum]
            [mpd-web-gui.core :refer [app-state]]))

(defn songs [state]
  (get-in state [:current-playlist] []))

(defn song-label [song]
  (str (:artist song) " - " (:title song)))

(defn now-playing-position []
  (get-in @app-state [:status :currentSong :position]))

(defn song-element-class [song]
  (str "list-group-item list-group-item-action"
       (when (= (:position song) (now-playing-position)) " active")))

(defn song-element [song]
  [:div {:key (:id song) :class (song-element-class song)} (song-label song)])

(rum/defc current-playlist < rum/reactive []
  [:div
   {:class "current-playlist list-group"}
   (map song-element (songs (rum/react app-state)))])
