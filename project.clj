;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at http://mozilla.org/MPL/2.0/.

(defproject pubsure-reader "0.3.0-SNAPSHOT"
  :description "Containium app for pubsure-ws/reader using pubsure-zk."
  :url "https://github.com/containium/Backend"
  :dependencies [[containium "0.3.0-SNAPSHOT"]
                 [pubsure/pubsure-ws "0.1.0-SNAPSHOT"]
                 [pubsure/pubsure-zk "0.1.0-SNAPSHOT"]]
  :exclusions [org.clojure/clojure com.taoensso/timbre]
  :containium {:start pubsure-reader.core/start
              :stop pubsure-reader.core/stop
              :ring {:handler pubsure-reader.core/app
                     :context-path "/pubsure"}
              :zk-connect-str "localhost:2181"})
