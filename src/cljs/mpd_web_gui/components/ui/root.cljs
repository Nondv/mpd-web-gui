(ns mpd-web-gui.components.ui.root
  (:require [rum.core :as rum]
            [mpd-web-gui.components.ui.now-playing :refer [now-playing]]
            [mpd-web-gui.components.ui.current-playlist :refer [current-playlist]]
            [mpd-web-gui.components.ui.controls :refer [controls]]
            [mpd-web-gui.core :refer [app-state]]))

(rum/defc root []
  [:div
   (now-playing)
   (controls)
   (current-playlist)])
