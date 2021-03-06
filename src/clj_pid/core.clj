(ns clj-pid.core
  (:require [clojure.string :as string])
  (:import java.io.File))

(def current 
  "Get current process PID"
  (memoize
   (fn []
     (-> (java.lang.management.ManagementFactory/getRuntimeMXBean)
         (.getName)
         (string/split #"@")
         (first)))))

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

(defn initialize! [pid-file]
  (doto pid-file
    save
    delete-on-shutdown!))
