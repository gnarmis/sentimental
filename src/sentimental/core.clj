(ns sentimental.core
	(gen-class)
	(:import [snowball-stemmer])
	(:use [stemmer.snowball]
		  	[opennlp.nlp]
		  	[clojure.java.io]))


(def eng-stemmer (stemmer "english"))

(def tokenizer (make-tokenizer "src/models/en-token.bin"))

(defn get-lines [fname]
  (with-open [r (reader fname)]
    (doall (line-seq r))))

(def stop-words
	(set (get-lines "src/stop_words.txt")))

(defn strip-stop-words [l]
	(filter (fn [x] (not (contains? stop-words x)))
			(set l)))

(defn bag-of-words [s]
	(set (map (fn [x] (eng-stemmer x)) 
			(strip-stop-words (tokenizer s)))))

(defn -main [& m]
	(println "Sentimental started."))