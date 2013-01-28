(ns alandipert.desiderata.test
  (:require [alandipert.desiderata :as d]))

(def t {:v 0 :sinks
        [{:v 1 :sinks
          [{:v 3 :sinks []}
           {:v 4 :sinks []}]}
         {:v 2 :sinks
          [{:v 5 :sinks
            [{:v 6 :sinks []}]}]}]})

(assert (= (map :v (d/bf-seq identity :sinks t)) [0 1 2 3 4 5 6]))

(.log js/console "__exit__")
