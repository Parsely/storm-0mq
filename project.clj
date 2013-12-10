(defproject storm/storm-0mq "0.9.0"
  :dependencies [[storm/storm-core "0.9.0.1"]
                 [backtype/jzmq "2.1.0"]
                 ]

  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :target-path "target"
  :javac-options ["-target" "1.6" "-source" "1.6"]
  :jvm-opts ["-Djava.library.path=/usr/local/lib:/opt/local/lib:/usr/lib"]

  :aot :all)
f