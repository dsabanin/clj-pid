# clj-pid

Tiny Clojure library to work retrieve current process PID and handle pid files

## Install

Available from <a href="https://clojars.org/clj-pid">Clojars</a>.

## Usage

```clj
(use '(clj-pid.core :as pid))

(def pid-file "/var/run/my-daemon.pid")

;; Get current process PID
(pid/current) ; => "39350"

;; Save pid file
(pid/save pid-file)

;; Delete pid file
(pid/delete pid-file) ; => true / false

;; Delete pid file on JVM shutdown
(pid/delete-on-shutdown! pid-file)
```
## License

Copyright (C) 2012 Dima Sabanin

Distributed under the Eclipse Public License, the same as Clojure.

