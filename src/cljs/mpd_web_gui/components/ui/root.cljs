(ns mpd-web-gui.components.ui.root
  (:require [rum.core :as rum]
            [mpd-web-gui.components.ui.now-playing :refer [now-playing]]
            [mpd-web-gui.components.ui.contents :refer [contents]]
            [mpd-web-gui.components.ui.controls :refer [controls]]
            [mpd-web-gui.core :refer [app-state]]))

(rum/defc root []
  [:div
   [:div {:class "mb-1"} (now-playing)]
   [:div {:class "mb-5"} (controls)]
   (contents)])
