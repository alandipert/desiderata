(ns alandipert.desiderata.invoke)

(defn gen-nonvariadic-invokes [f]
  (for [arity (range 1 21),
        :let [args (repeatedly arity gensym)]]
    `(~'invoke [~@args] (~f ~@args))))

(defn gen-variadic-invoke [f]
  (let [args (repeatedly 22 gensym)]
    `(~'invoke [~@args] (apply ~f ~@(butlast args) ~(last args)))))

(defn gen-apply-to [f]
  `(~'applyTo [this# args#] (apply ~f this# args#)))

(defn extend-IFn [f]
  `(clojure.lang.IFn
    ~@(gen-nonvariadic-invokes f)
    ~(gen-variadic-invoke f)
    ~(gen-apply-to f)))

(defmacro reifn
  "Like reify, but accepts a function f as a first argument that is
  used to implement clojure.lang.IFn. f should accept at least one
  argument, 'this'."
  [f & opts+specs]
  `(reify
     ~@(extend-IFn f)
     ~@opts+specs))

(defmacro deftypefn
  "Like deftype, but accepts a function f before any specs.  f should
  accept at least one argument, 'this'."
  [name [& fields] f & opts+specs]
  `(deftype ~name [~@fields]
     ~@(extend-IFn f)
     ~@opts+specs))
