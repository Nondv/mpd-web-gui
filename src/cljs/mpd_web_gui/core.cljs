(ns mpd-web-gui.core
  (:require [rum.core :as rum]))

(enable-console-print!)

(def initial-state
  {:text "Hello Chestnut!"
   :status {:current-song {:artist "12 Stones"
                           :title "The Way I Feel"
                           :album "12 Stones"
                           :id "3687"
                           :position "2"}}})

(defonce app-state (atom initial-state))
