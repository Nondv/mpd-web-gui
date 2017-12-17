(ns mpd-web-gui.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [mpd-web-gui.core-test]
   [mpd-web-gui.common-test]))

(enable-console-print!)

(doo-tests 'mpd-web-gui.core-test
           'mpd-web-gui.common-test)
