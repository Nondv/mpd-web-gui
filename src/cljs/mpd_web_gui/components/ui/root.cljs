(ns mpd-web-gui.components.ui.root
  (:require [rum.core :as rum]
            [mpd-web-gui.components.ui.now-playing :refer [now-playing]]
            [mpd-web-gui.core :refer [app-state]]))

(rum/defc root []
  (now-playing))
