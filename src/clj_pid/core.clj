(ns clj-pid.core
  (:require [clojure.string :as string])
  (:import java.io.File))

(defn current 
  "Get current process PID" 
  []
  (-> (java.lang.management.ManagementFactory/getRuntimeMXBean)
    (.getName)
    (string/split #"@")
    (first)))

(defn save
  "Save current PID to a pid-file"
  [pid-file]
  (spit pid-file (current)))

(defn delete
  "Delete pid-file"
  [pid-file]
  (.delete (File. pid-file)))

(defn delete-on-shutdown!
  "Delete pid-file on JVM shutdown"
  [pid-file]
  (.addShutdownHook (Runtime/getRuntime) (Thread. #(delete pid-file))))
