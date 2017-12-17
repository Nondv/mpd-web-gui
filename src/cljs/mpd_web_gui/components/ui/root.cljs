(ns mpd-web-gui.components.ui.root
  (:require [rum.core :as rum]
            [mpd-web-gui.components.ui.now-playing :refer [now-playing]]
            [mpd-web-gui.components.ui.control-button :refer [control-button]]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.core :refer [app-state]]))

(defn onclick-dummy []
  (js/console.log "TODO"))

(rum/defc root []
  [:div
   (now-playing)
   [:div
    (control-button "media-skip-backward" api/play-previous)
    (control-button "media-pause" onclick-dummy)
    (control-button "media-play" onclick-dummy)
    (control-button "media-skip-forward" api/play-next)]])
