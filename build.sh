#!/bin/sh

javac -cp ".:lib/*" -d build $(find ./src/main/java -type f -name "*.java")
cd build
jar cf ../dist/wintermute.jar $(find . -type f)
