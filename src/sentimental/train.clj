(ns sentimental.train
	(:require [clojure.string :as string])
	(:use [opennlp.nlp]
        [cheshire.core]
        [clojure.pprint]
        [clojure.java.io]))

(def temp-corpus
	(sentimental.core/get-lines "src/subjectivity_lexicon.tff"))

;(def eg1 (sentimental.core/tokenizer (first temp-corpus)))

(defn create-hashmap [l]
	(let [a (map #(string/split % #"=") l)
        b (into {} 
          (for [[k v] a] 
            [(keyword k) v]))]
        b))

(defn process [s]
	(create-hashmap (sentimental.core/tokenizer s)))

(def corpus (vec (map process temp-corpus)))


(defn stemmed-only [col]
	(filter (fn [h] (= (:stemmed1 h) "y"))
			    col))

;(pprint stemmed-only)

(defn by-subj 
  "filter by subject, such as strongsubj, weaksubj"
  [col subj]
  (filter (fn [h] (= (:type h)
                     subj))
          col))

(defn by-type 
  "filter by type, such as positive, negative, or neutral"
  [col type]
  (filter (fn [h] (= (:priorpolarity h)
                     type))
          col))

(defn create-train-str [h]
  (let [type (:type h)
        pp  (:priorpolarity h)
        word (:word1 h)]
        (str (name type) "-" pp " " word "\n")))

(defn append-to-file [s f]
  (with-open [wrtr (writer f :append true)]
    (.write wrtr s)))

(defn append-all-to-file [subj type]
  (map  (fn [h] (append-to-file (create-train-str h)
                                "src/sentiment.train"))
        (by-type (by-subj (stemmed-only corpus) 
                          subj)
                  type)))





