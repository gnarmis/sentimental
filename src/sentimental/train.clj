(ns sentimental.train
	(:require [clojure.string :as string])
	(:use [opennlp.nlp]
		  [cheshire.core]))

(def temp-corpus (sentimental.core/get-lines "src/subjectivity_lexicon.tff"))

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

