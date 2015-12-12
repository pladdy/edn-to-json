(ns edn-to-json.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.java.io :as io]
            [clojure.string :as st]
            [clojure.edn :as e])
  (:gen-class))

;; keep functions public in case this gets used as a library instead of cli

(defn is-edn-file?
  "Given a file-string, make sure it exists and that it's an edn file"
  [file]
  (and
    (.exists (io/file file))
    (try
      (e/read-string (slurp file))
      (catch Exception e false))))

(def cli-options
  [["-i" "--input" "Input to parse; defaults to STDIN but can also take an EDN file"
    :default nil
    :validate [is-edn-file? "File required"]]
   ["-o" "--output" "File to output JSON to; defaults to STDOUT"
    :default nil]
   ["-h" "--help"]])

(defn usage
  "Display the help/usage info"
  [summary]
  (->> ["Command line tool to convert EDN data into JSON."
        ""
        "Usage: edn2json [options]"
        ""
        "Options:"
        summary
        ""]
       (st/join \newline)))

;; private

(defn- exit
  "Exit with a message"
  ([status] (exit status nil))
  ([status message]
   (if message (println message))
   (System/exit status)))

(defn -main
  "Launches a command line tool that will convert an edn file to json."
  [& args]
  (let [{:keys [options arguments errors summary] :as parsed-opts}
          (parse-opts args cli-options)]
    (cond
      (nil? (-> options
                :options
                :input)) (exit 0 (usage summary))
      :else (println parsed-opts))))
