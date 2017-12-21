(ns mpd-web-gui.components.ui.volume-control
  (:require [rum.core :as rum]
            [mpd-web-gui.api :as api]
            [mpd-web-gui.core :refer [app-state wrap-state]]))


(defn current-volume []
  (:volume (:status @app-state)))

(defn init-slider [state]
  (let [slider (.slider (js/$ "#volume-control")
                        #js {:min 0, :max 100, :value (current-volume)})]
    ;; TODO: use timeout for sliding
    (.on slider "change" #(api/set-volume (.. % -value -newValue)))
    (assoc state :slider slider)))

(defn set-slider-value [state]
  (.slider (:slider state) "setValue" (current-volume))
  state)

(rum/defcs volume-control <
  rum/reactive
  {:did-mount init-slider
   :did-update set-slider-value}
  [state]
  (rum/react app-state)
  [:div
   [:div (str "Volume: " (current-volume))]
   [:input {:id "volume-control"}]])
