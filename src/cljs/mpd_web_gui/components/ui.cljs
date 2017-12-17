(ns mpd-web-gui.components.ui
  (:require [com.stuartsierra.component :as component]
            [mpd-web-gui.core :refer [render]]))

(defrecord UIComponent []
  component/Lifecycle
  (start [component]
    (render)
    component)
  (stop [component]
    component))

(defn new-ui-component []
  (map->UIComponent {}))
