(ns sentimental.test.core
  (:use [sentimental.core])
  (:use [clojure.test]))

(deftest strong-positive
  (is (= (categorize "I am happy")
         "strongsubj-positive")))

(deftest strong-negative
  (is (= (categorize "I am sad")
         "strongsubj-negative")))

;; Cases from Issue 1

(deftest neg-1
  (let [t (categorize "I don't like it.")]
    (is (or (= t
               "strongsubj-negative")
            (= t
               "weaksubj-negative")))))

(deftest neg-2
  (let [t (categorize "The movie was bad.")]
    (is (or (= t
               "strongsubj-negative")
            (= t
               "weaksubj-negative")))))

(deftest neg-3
  (let [t (categorize "The movie sucked.")]
    (is (or (= t
               "strongsubj-negative")
            (= t
               "weaksubj-negative")))))


(deftest neu-1
  (let [t (categorize "I waited.")]
    (is (or (= t
               "strongsubj-neutral")
            (= t
               "weaksubj-neutral")))))

(deftest neu-2
  (let [t (categorize "foo")]
    (is (or (= t
               "strongsubj-neutral")
            (= t
               "weaksubj-neutral")))))

(deftest neu-3
  (let [t (categorize "bar")]
    (is (or (= t
               "strongsubj-neutral")
            (= t
               "weaksubj-neutral")))))

