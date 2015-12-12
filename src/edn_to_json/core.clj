(ns edn-to-json.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.java.io :as io]
            [clojure.edn :as e])
  (:gen-class))

(defn is-edn-file?
  "Given a file-string, make sure it exists and that it's an edn file"
  [file]
  (and
    (.exists (io/file file))
    (try
      (e/read-string (slurp file))
      (catch Exception e false))))

(def cli-options
  [["-e" "--edn-file" "EDN file to convert to JSON"
    :default nil
    :validate [is-edn-file? "File required"]]
   ["-o" "--output" "File to output JSON to; defaults to STDOUT"
    :default nil]
   ["-h" "--help"]])

(defn -main
  "Launches a command line tool that will convert an edn file to json."
  [& args]
  (let [{:keys [options arguments errors summary] :as parsed-opts}
          (parse-opts args cli-options)]
    (println parsed-opts)))
