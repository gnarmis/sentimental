(ns sentimental.core
	(:import [snowball-stemmer])
	(:require [sentimental.train :as tr])
	(:use [stemmer.snowball]
		  	[opennlp.nlp]
		  	[clojure.java.io]))


(def eng-stemmer (stemmer "english"))
(def tokenizer (make-tokenizer "src/models/en-token.bin"))
(def detokenizer (make-detokenizer "src/models/english-detokenizer.xml"))
; the actual categorizer
(def categorize (make-document-categorizer tr/senti-model))

(defn stop-words []
	(set (sentimental.train/get-lines "src/stop_words.txt")))

(defn strip-stop-words [l]
	(filter (fn [x] (not (contains? (stop-words) x)))
			(set l)))

(defn bag-of-words 
	"Converts a string into a set of unique words/elements, 
	each stemmed (in English)"
	[s]
	(set (map (fn [x] (eng-stemmer x)) 
			(strip-stop-words (tokenizer s)))))

(defn compact 
	"Takes a string, strips out stop words, and stems each word. 
	Returns a string"
	[s]
	(detokenizer (bag-of-words s)))