#!/bin/sh

java -jar /solution/app/build/Main.jar "$@" || echo "run error code: $?"
