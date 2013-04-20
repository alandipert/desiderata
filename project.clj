(defproject alandipert/desiderata "1.0.2"
  :description "A ClojureScript bag of tricks."
  :url "https://github.com/alandipert/desiderata"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :source-paths ["src/cljs" "src/clj"]
  :cljsbuild {:builds
              {:test
               {:source-paths ["test"]
                :compiler {:output-to "public/test.js"
                           :optimizations :advanced}
                :jar false}}
              :test-commands {"unit" ["phantomjs" "test/runner.js"]}})
