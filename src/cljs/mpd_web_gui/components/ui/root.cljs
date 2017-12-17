(ns mpd-web-gui.components.ui.root
  (:require [rum.core :as rum]
            [mpd-web-gui.core :refer [app-state]]))

(rum/defc root < rum/reactive []
   [:h1 (:text (rum/react app-state))])
