#!/bin/sh


javac -cp ".:lib/*" -d build $(find ./src/main/java -type f -name "*.java")
mkdir dist
cd build
jar cf ../dist/wintermute.jar $(find . -type f)
