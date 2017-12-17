(ns mpd-web-gui.api
  (:require [ajax.core :as ajax]))

(defn status [handler]
  (ajax/POST "http://192.168.0.14:6789"
             {:response-format (ajax/json-response-format {:keywords? true})
              :format (ajax/json-request-format)
              :params  {:action "status"}
              :handler handler}))