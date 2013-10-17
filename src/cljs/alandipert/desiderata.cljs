(ns alandipert.desiderata)

(defn bf-seq
  "Like tree-seq but traversal is breadth-first instead of depth-first."
  [branch? children root]
  (letfn [(walk [queue]
            (when-let [node (peek queue)]
              (lazy-seq
               (cons node (walk (into (pop queue)
                                      (if (branch? node) (children node))))))))]
    (walk (conj cljs.core.PersistentQueue.EMPTY root))))
