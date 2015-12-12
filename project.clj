(defproject edn-to-json "0.1.0-SNAPSHOT"
  :description "Simple cli tool to conver edn files to json"
  :url "http://example.com/FIXME"
  :license {:name ""
            :url ""}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [cheshire "5.5.0"]]
  :main ^:skip-aot edn-to-json.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
