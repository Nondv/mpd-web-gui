(ns mpd-web-gui.components.ui.playlists
  (:require [rum.core :as rum]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.components.ui.control-button :refer [control-button]]
            [mpd-web-gui.core :refer [app-state wrap-state]]))

(defn load-playlists []
  (api/playlists
   (fn [response]
     (swap! app-state #(assoc % :playlists (:names response))))))

(defn render-playlist [name]
  [:div
   {:key name :class "list-group-item"}
   (control-button "plus" {:size :small} #(api/load-playlist name))
   name])

(rum/defc playlists <
  rum/reactive
  {:did-mount (wrap-state load-playlists)}
  []

  [:div
   [:div
    {:class "list-group"}
    (map render-playlist (:playlists (rum/react app-state)))]])
