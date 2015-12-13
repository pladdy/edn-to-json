(ns edn-to-json.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [edn-to-json.core :as ej]))

(def not-nil? (complement nil?))

(deftest edn->json-test
  (testing "edn text is converted to json"))

(deftest is-edn-file-test
  (let [test-edn-file "is-edn-file-test.edn"]
    (testing "an edn file"
      (spit test-edn-file "{:foo :bar}")
      (is (ej/is-edn-file? test-edn-file) "  file should be edn format")
      (io/delete-file test-edn-file))
    (testing "a non edn file"
      (spit test-edn-file "{:foo :bar")
      (is (not (ej/is-edn-file? test-edn-file)) "  file should not be edn format")
      (io/delete-file test-edn-file))))

(deftest usage-test
  (testing "make sure usage returns content"
    (let [test-summary "-a --a-thing A thing"]
      (and
        (is (not-nil? (ej/usage test-summary))) "  shouldn't be nil")
        (is (nil? (re-find #"^$" (ej/usage test-summary))) "  shouldn't be empty string"))))
