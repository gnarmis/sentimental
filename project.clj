(defproject sentimental "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [clojure-opennlp "0.1.10"]
                 [org.clojars.gnarmis/snowball-stemmer "0.1.1-SNAPSHOT"]
                 [cheshire "4.0.0"]]
  :main sentimental.core
  :aot  [sentimental.core])