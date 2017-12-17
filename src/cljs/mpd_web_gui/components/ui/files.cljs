(ns mpd-web-gui.components.ui.files
  (:require [rum.core :as rum]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.components.ui.control-button :refer [control-button]]
            [mpd-web-gui.core :refer [app-state wrap-state]]))

(defn load-files []
  (api/files
   (fn [response]
     (swap! app-state #(assoc % :files (:files response))))))

(defn set-file-filter [v]
  (swap! app-state #(assoc % :file-filter v)))

(defn render-file [filename]
  [:div
   {:key filename :class "list-group-item"}
   (control-button "plus" {:size :small} #(api/add-to-queue filename))
   filename])

(defn file-filter []
  (:file-filter @app-state ""))

(defn filtered-files []
  (let [all-files (:files @app-state [])
        lc #(clojure.string/lower-case %)]
    (if (< 3 (count (file-filter)))
      (filter #(clojure.string/includes? (lc %) (lc (file-filter))) all-files)
      all-files)))


(rum/defc files <
  rum/reactive
  {:did-mount (wrap-state load-files)}
  []

  (let [files (:files (rum/react app-state))]
    [:div
     [:div "Всего: " (count files)]
     [:div "Отображается: " (min (count files) 500)]
     [:input {:class     "form-control"
              :value (file-filter)
              :placeholder "Поиск"
              :on-change #(set-file-filter(.. % -target -value))}]
     [:button {:class "btn btn-primary"
               :on-click #(api/add-to-queue (filtered-files))}
      "Добавить все (с учетом фильтра)"]
     [:div
      {:class "files-list list-group"}
      (map render-file (take 500 (filtered-files)))]]))
