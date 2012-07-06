(ns sentimental.core
	(gen-class)
	(:import [snowball-stemmer])
	(:use [stemmer.snowball]
		  [opennlp.nlp]))


(def eng-stemmer (stemmer "english"))

(def tokenizer (make-tokenizer "src/models/en-token.bin"))

(defn -main [& m]
	(println "Sentimental started."))