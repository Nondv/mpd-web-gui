(ns mpd-web-gui.components.ui.controls
  (:require [rum.core :as rum]
            [mpd-web-gui.components.ui.control-button :refer [control-button]]
            [mpd-web-gui.components.ui.volume-control :refer [volume-control]]
            [mpd-web-gui.components.ui.state-switch   :refer [state-switch]]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.core :refer [app-state]]))

(rum/defc controls < rum/reactive []
  (rum/react app-state)

  [:div
   [:div
    (control-button "media-skip-backward" api/play-previous)
    (control-button "media-pause" api/pause)
    (control-button "media-play" #(api/play))
    (control-button "media-skip-forward" api/play-next)]

   [:div
    {:class "mb-4"}
    (state-switch "random" {:active (get-in @app-state [:status :random])} api/toggle-random)
    (state-switch "loop" {:active (get-in @app-state [:status :repeat])} api/toggle-repeat)]

   (volume-control)])
