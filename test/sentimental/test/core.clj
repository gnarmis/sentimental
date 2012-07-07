(ns sentimental.test.core
  (:use [sentimental.core])
  (:use [clojure.test]))

(deftest strong-positive
  (is (= (categorize "I am happy")
         "strongsubj-positive")))

(deftest strong-negative
  (is (= (categorize "I am sad")
         "strongsubj-negative")))

