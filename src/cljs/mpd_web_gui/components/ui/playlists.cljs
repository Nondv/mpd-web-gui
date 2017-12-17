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

(defn on-playlist-save [e]
  (.preventDefault e)
  (api/save-playlist (.val (js/$ "#name-input")) load-playlists))

(rum/defc playlists <
  rum/reactive
  {:did-mount (wrap-state load-playlists)}
  []

  [:div
   [:form {:class "form-inline", :on-submit on-playlist-save}
    [:div {:class "input-group"}
     [:input {:id "name-input" :class "form-control", :placeholder "Название"}]
     [:button {:class "btn btn-outline-dark"} "Сохранить"]]
    [:small {:class "form-text text-muted ml-2"} "Сохраняется текущая очередь. Старый плейлист будет перезаписан"]]
   [:div
    {:class "list-group"}
    (map render-playlist (:playlists (rum/react app-state)))]])
