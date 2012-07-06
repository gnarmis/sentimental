(ns sentimental.core
	(gen-class)
	(:import [snowball-stemmer])
	(:use [stemmer.snowball]))


(def eng (stemmer "english"))

(defn -main [& m]
	(println "Sentimental started."))