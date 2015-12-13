(ns edn-to-json.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.java.io :as io]
            [clojure.string :as st]
            [clojure.edn :as e])
  (:gen-class))

;; keep functions public in case this gets used as a library instead of cli

(defn edn->json
  "Take edn input and ouput json to the given output"
  [input output]
  ())

(defn is-edn-file?
  "Given a file-string, make sure it exists and that it's an edn file"
  [file]
  (and
    (.exists (io/file file))
    (try
      (do (e/read-string (slurp file)) true)
      (catch Exception e false))))

(defn is-edn?
  "Given a string ensure it's edn format."
  [edn-string]
  (try
    (do (e/read-string edn-string) true)
    (catch Exception e false)))

(def cli-options
  [["-i" "--input INPUT" "Input to parse; defaults to STDIN but can also take an EDN file"
    :default nil
    :validate [#(or (is-edn-file? %) (is-edn? %)) "EDN file or input is required"]]
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
    ;; TODO: remove this when done
    (println parsed-opts)
    (cond
      (nil? (:input options)) (exit 1 (usage summary))
      (:help options) (exit 0 (usage summary)))
    ;; convert the edn to json
    (edn->json (:input options) (:output options))))

