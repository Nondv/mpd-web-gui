(ns mpd-web-gui.components.ui.state-switch
  (:require [rum.core :as rum]))

(defn icon-class [icon]
  (str "oi oi-" icon))

(defn button-class [options]
  ["btn"
   (case (:active options)
     true "btn-primary"
     "btn-secondary")
   (case (:size options)
     :large "btn-lg"
     :medium nil
     "btn-sm")])

(rum/defc state-switch [icon options on-click]
  [:button {:class (button-class options)
            :on-click on-click}
   [:span {:class (icon-class icon)}]])
