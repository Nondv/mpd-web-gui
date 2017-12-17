(ns mpd-web-gui.components.ui.control-button
  (:require [rum.core :as rum]))

(defn icon-class [icon]
  (str "oi oi-" icon))

(rum/defc control-button [icon on-click]
  [:button {:class "btn btn-outline-dark btn-lg"
            :on-click on-click}
   [:span {:class (icon-class icon)}]])
