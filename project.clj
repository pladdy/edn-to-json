(defproject edn-to-json "0.1.0-SNAPSHOT"
  :description "Simple cli tool to conver edn files to json"
  :url "http://example.com/FIXME"
  :license {:name ""
            :url ""}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [cheshire "5.5.0"]]
  :main ^:skip-aot edn-to-json.core
  :bin {:name "edn2json" :bin-path "bin"}
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-bin "0.3.5"]]}})
