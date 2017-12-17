(ns mpd-web-gui.components.ui
  (:require [com.stuartsierra.component :as component]
            [rum.core :as rum]

            [mpd-web-gui.components.ui.root :refer [root]]))

(defn render []
  (rum/mount (root) (. js/document (getElementById "app"))))

(defrecord UIComponent []
  component/Lifecycle
  (start [component]
    (render)
    component)
  (stop [component]
    component))

(defn new-ui-component []
  (map->UIComponent {}))
