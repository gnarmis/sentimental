(ns sentimental.train
	(:require [clojure.string :as string])
	(:use [opennlp.nlp]
        [opennlp.tools.train]
        [cheshire.core]
        [clojure.pprint]
        [clojure.java.io]))

(def tokenizer (make-tokenizer "src/models/en-token.bin"))
(def senti-model (train-document-categorization "src/models/sentiment.train"))

(defn get-lines [fname]
  (with-open [r (reader fname)]
    (doall (line-seq r))))

(defn temp-corpus []
	(get-lines "src/subjectivity_lexicon.tff"))

(defn create-hashmap [l]
	(let [a (map #(string/split % #"=") l)
        b (into {} 
          (for [[k v] a] 
            [(keyword k) v]))]
        b))

(defn process [s]
	(create-hashmap (tokenizer s)))

(defn corpus [] 
  (vec (map process (temp-corpus))))


(defn stemmed-only [col]
	(filter (fn [h] (= (:stemmed1 h) "y"))
			    col))

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
                                "src/models/sentiment.train"))
        (by-type (by-subj (stemmed-only (corpus)) 
                          subj)
                  type)))

; (append-all-to-file "strongsubj" "positive")
; (append-all-to-file "weaksubj" "positive")
; (append-all-to-file "strongsubj" "negative")
; (append-all-to-file "weaksubj" "negative")
; (append-all-to-file "strongsubj" "neutral")
; (append-all-to-file "weaksubj" "neutral")




