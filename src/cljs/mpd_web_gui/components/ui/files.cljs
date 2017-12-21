(ns mpd-web-gui.components.ui.files
  (:require [rum.core :as rum]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.components.ui.control-button :refer [control-button]]
            [mpd-web-gui.core :refer [file-uris file-filter wrap-state]]))

(defn load-files []
  (api/files #(reset! file-uris (:files %))))

(defn set-file-filter [v]
  (reset! file-filter v))

(defn render-file [filename]
  [:div
   {:key filename :class "list-group-item"}
   (control-button "plus" {:size :small} #(api/add-to-queue filename))
   filename])

(defn filtered-files []
  (let [all-files @file-uris
        lc #(clojure.string/lower-case %)]
    (if (< 3 (count @file-filter))
      (filter #(clojure.string/includes? (lc %) (lc @file-filter)) all-files)
      all-files)))

(rum/defc files <
  rum/reactive
  {:did-mount (wrap-state load-files)}
  []

  (rum/react file-uris)
  (rum/react file-filter)

  (let [f-files (filtered-files)]
    [:div
     [:div "Всего: " (count f-files)]
     [:div "Отображается: " (min (count f-files) 500)]
     [:input {:class     "form-control"
              :value @file-filter
              :placeholder "Поиск"
              :on-change #(set-file-filter(.. % -target -value))}]
     [:button {:class "btn btn-primary"
               :on-click #(api/add-to-queue f-files)}
      "Добавить все (с учетом фильтра)"]
     [:div
      {:class "files-list list-group"}
      (map render-file (take 500 f-files))]]))
