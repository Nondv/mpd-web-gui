(ns mpd-web-gui.components.ui.current-playlist
  (:require [rum.core :as rum]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.core :refer [app-state]]))

(defn songs [state]
  (get-in state [:current-playlist] []))

(defn song-label [song]
  (if (or (:artist song) (:title song))
    (str (:artist song) " - " (:title song))
    (:file song)))

(defn now-playing-position []
  (get-in @app-state [:status :currentSong :position]))

(defn song-element-class [song]
  (str "list-group-item list-group-item-action"
       (when (= (:position song) (now-playing-position)) " active")))

(defn song-element [song]
  [:div {:key (:id song) :class (song-element-class song)} (song-label song)])

(rum/defc current-playlist < rum/reactive []
  [:div
   [:button {:class "btn btn-outline-danger"
             :on-click api/clear-queue}
    "Очистить"]
   [:div
    {:class "current-playlist list-group"}
    (map song-element (songs (rum/react app-state)))]])
