#!/bin/sh

cd app
javac -d build *.java

cd build
jar cfe Main.jar Main *
