(ns mpd-web-gui.components.ui.control-button
  (:require [rum.core :as rum]))

(defn icon-class [icon]
  (str "oi oi-" icon))

(defn button-class [options]
  ["btn btn-outline-dark"
   (case (:size options)
     :large "btn-lg"
     :small "btn-sm"
     nil)])

(rum/defc control-button
  ([icon on-click] (control-button icon {:size :large} on-click))
  ([icon options on-click]
   [:button {:class (button-class options)
             :on-click on-click}
    [:span {:class (icon-class icon)}]]))
