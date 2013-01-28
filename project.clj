(defproject alandipert/desiderata "1.0.1"
  :description "A ClojureScript bag of tricks."
  :url "https://github.com/alandipert/desiderata"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-cljsbuild "0.3.0"]]
  :source-paths ["src/cljs"]
  :cljsbuild {:builds
              {:test
               {:source-paths ["test"]
                :compiler {:output-to "public/test.js"
                           :optimizations :advanced}
                :jar false}}
              :test-commands {"unit" ["phantomjs" "test/runner.js"]}})
