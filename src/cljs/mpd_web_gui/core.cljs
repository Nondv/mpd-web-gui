(ns mpd-web-gui.core
  (:require [rum.core :as rum]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"}))
