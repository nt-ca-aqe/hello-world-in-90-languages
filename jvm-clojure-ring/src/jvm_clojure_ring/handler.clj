(ns jvm-clojure-ring.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.json-response :refer [json-response]]))

(defroutes app-routes
  (ANY "/say-hello" request
    (json-response {:message (str "Hello " (get-in request [:query-params "name"] "World") "!")}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes
                 (assoc-in site-defaults
                           [:security :anti-forgery] false)))
