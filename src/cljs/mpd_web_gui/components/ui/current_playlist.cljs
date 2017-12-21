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

(defn song-element-id [position]
  (str "song-" position))

(defn song-element [song]
  [:div
   {:key (:id song)
    :id (song-element-id  (:position song))
    :class (song-element-class song)}
   [:span
    {:class "current-playlist__song-label", :on-click #(api/play (:position song))}
    (song-label song)]])

(defn move-to-song [state]
  (let [last-position (:last-position state)
        current-position (now-playing-position)
        element (js/document.getElementById (song-element-id current-position))]
    (when (and element (not= current-position last-position))
      (.scrollIntoView element true))
    (assoc state :last-position current-position)))

(rum/defc current-playlist <
  rum/reactive
  {:did-update move-to-song}
  []

  [:div
   [:button {:class "btn btn-outline-danger"
             :on-click api/clear-queue}
    "Очистить"]
   [:div
    {:class "current-playlist list-group"}
    (map song-element (songs (rum/react app-state)))]])
