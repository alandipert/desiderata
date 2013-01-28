(ns alandipert.desiderata)

(defn bf-seq
  [branch? children root]
  "Like tree-seq but traversal is breadth-first instead of depth-first."
  (letfn [(walk [queue]
            (when-let [node (peek queue)]
              (lazy-seq
               (cons node (walk (into (pop queue)
                                      (if (branch? node) (children node))))))))]
    (walk (conj cljs.core.PersistentQueue/EMPTY root))))