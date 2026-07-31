#!/bin/sh
# Gradle wrapper script
GRADLE_VERSION=8.2
JAR_PATH="gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$JAR_PATH" ]; then
    echo "Downloading Gradle wrapper..."
    mkdir -p gradle/wrapper
    curl -L "https://raw.githubusercontent.com/gradle/gradle/v$GRADLE_VERSION/gradle/wrapper/gradle-wrapper.jar" -o "$JAR_PATH"
fi
exec java -jar "$JAR_PATH" "$@"
