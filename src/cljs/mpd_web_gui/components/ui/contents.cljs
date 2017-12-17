(ns mpd-web-gui.components.ui.contents
  (:require [rum.core :as rum]
            [mpd-web-gui.components.ui.current-playlist :refer [current-playlist]]
            [mpd-web-gui.components.ui.files :refer [files]]
            [mpd-web-gui.core :refer [app-state]]))

(defn current-tab []
  (get-in @app-state [:current-tab] :current-playlist))

(defn tab-active? [tab-keyword]
  (= (current-tab) tab-keyword))

(defn set-tab [tab-keyword]
  (swap! app-state #(assoc % :current-tab tab-keyword)))

(defn render-pill [label tab-keyword]
  [:li
   {:class "nav-item"}
   [:a
    {:class ["nav-link" (when (tab-active? tab-keyword) "active")]
     :on-click #(set-tab tab-keyword)
     :href "#"}
    label]])

(rum/defc contents < rum/reactive []
  (rum/react app-state)
  [:div
   [:ul
    {:class "nav nav-pills"}
    (render-pill "Очередь" :current-playlist)
    (render-pill "Файлы" :files)
    (render-pill "TODO" :todo)]
   (case (current-tab)
     :current-playlist (current-playlist)
     :files (files)
     nil)])
