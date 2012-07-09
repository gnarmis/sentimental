# sentimental

A basic sentiment analyzer, based on clojure-opennlp and using the subjectivity lexicon. Check out the training file in `src/models/sentiment.train`. Also, the subjectivity lexicon parsed into JSON is provided under `src/parsed_lexicon.json`.

## Usage

In the REPL (after running `lein repl`):

```clojure
(use 'sentimental.core)
(categorize "I am happy!")
; if working with a lot of text, compact will provide
; a "bag-of-words" representation with stop words removed
; and stemming of each word (using Snowball)
(categorize (compact long-document))
```

## License

Distributed under the Eclipse Public License, the same as Clojure.