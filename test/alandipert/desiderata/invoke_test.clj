(ns alandipert.desiderata.invoke-test
  (:require [clojure.test :refer :all]
            [alandipert.desiderata.invoke :refer [reifn deftypefn]]))

(deftest reifn-tests
  (is ((reifn (constantly true))))
  (let [r (reifn
           (fn [this s] (str this " " s))
           Object (toString [_] "hi"))]
    (is (= "hi" (str r)))
    (is (= "hi there!" (r "there!")))))

(deftypefn Foo [s]
  (fn [this & ss] (apply str this ss))
  Object (toString [_] s))

(deftypefn Bar [n]
  (fn
    ([this a] (Bar. (+ (.-n this) a)))
    ([this a b & more] (reduce #(%1 %2) this (list* a b more))))
  Object (toString [_] (str n)))

(deftypefn MyStr [s]
  (fn
    ([this x] (MyStr. (str (.-s this) x)))
    ([this x y & more] (reduce #(%1 %2) this (list* x y more))))
  Object (toString [_] s))

(deftest deftypefn-tests  
  (is (= "hi" (str (Foo. "hi"))))
  (is (= "abc" ((Foo. "a") "b" "c")))

  (is (= 6 (.-n ((Bar. 1) 2 3))))
  (is (= "10" (str ((Bar. 1) 2 3 4))))

  (is (= "cba" (str (reduce #((MyStr. %2) %1) "abc")))))
