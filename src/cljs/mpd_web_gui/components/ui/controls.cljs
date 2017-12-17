(ns mpd-web-gui.components.ui.controls
  (:require [rum.core :as rum]
            [mpd-web-gui.components.ui.control-button :refer [control-button]]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.core :refer [app-state]]))

(rum/defc controls []
  [:div
    (control-button "media-skip-backward" api/play-previous)
    (control-button "media-pause" api/pause)
    (control-button "media-play" #(api/play))
    (control-button "media-skip-forward" api/play-next)])
