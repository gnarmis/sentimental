(defproject org.clojars.gnarmis/sentimental "0.1.1-SNAPSHOT"
  :description "A basic sentiment analyzer, based on clojure-opennlp and using the subjectivity lexicon."
  :url "http://github.com/gnarmis/sentimental"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [clojure-opennlp "0.1.10"]
                 [org.clojars.gnarmis/snowball-stemmer "0.1.1-SNAPSHOT"]
                 [cheshire "4.0.0"]]
  :main sentimental.core)