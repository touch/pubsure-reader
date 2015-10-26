;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at http://mozilla.org/MPL/2.0/.

(ns pubsure-reader.core
  (:require [pubsure-ws.reader :as reader]
            [pubsure-zk.directory :as directory]))


(def app nil)

(def reader-state nil)
(def zk-dir nil)

(defn start
  "Start the app."
  [systems config]
  (let [zk-connect-str (-> config :containium :zk-connect-str)
        zk-dir (directory/start-directory zk-connect-str)
        [reader-state ring-app] (reader/make-app zk-dir)]
    (alter-var-root #'app (constantly ring-app))
    (alter-var-root #'reader-state (constantly reader-state))
    (alter-var-root #'zk-dir (constantly zk-dir))
    [reader-state zk-dir]))

(defn stop [[reader-state zk-dir]]
  (reader/stop-app reader-state)
  (directory/stop-directory zk-dir))
