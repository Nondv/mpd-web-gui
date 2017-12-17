(ns mpd-web-gui.components.ui.files
  (:require [rum.core :as rum]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.components.ui.control-button :refer [control-button]]
            [mpd-web-gui.core :refer [app-state]]))

(defn load-files []
  (api/files
   (fn [response]
     (swap! app-state #(assoc % :files (:files response))))))

(defn wrap-state [f]
  (fn [state]
    (f)
    state))

(defn render-file [filename]
  [:div
   {:key filename :class "list-group-item"}
   filename])

(rum/defc files <
  rum/reactive
  {:did-mount (wrap-state load-files)}
  []

  (let [files (:files (rum/react app-state) [])]
    [:div
     [:div "Всего: " (count files)]
     [:div "Отображается: " (min (count files) 500)]
     [:div
      {:class "files-list list-group"}
      (map render-file (take 500 files))]]))
