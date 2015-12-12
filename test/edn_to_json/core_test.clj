(ns edn-to-json.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [edn-to-json.core :as ej]))

(deftest is-edn-file-test
  (testing "a given file is edn"
    (let [test-edn-file "is-edn-file-test.edn"]
      (spit test-edn-file "{:foo :bar}")
      (is (ej/is-edn-file? test-edn-file) "  file should be edn format")
      (io/delete-file test-edn-file))))


