#!/usr/bin/env bash

set -e

RELEASE_VERSION=${1:-1.2.1}

# Install moduals to local repo. 
mvn clean install

# Build the JLink image.
mvn -pl GUI javafx:jlink

OS_NAME=$(uname -s)

case "$OS_NAME" in
  Linux*)   OS=linux; ICON="GUI/src/main/resources/Icons/Luminara-Main-Icon.png";;
  Darwin*)  OS=macos; ICON="GUI/src/main/resources/Icons/Luminara-Main-Icon.png";;
  MINGW*|MSYS*|CYGWIN*) OS=windows; ICON="GUI/src/main/resources/Icons/Luminara-Main-Icon.ico";;
  *)  OS=unknown; ICON="";;
esac




# Package into an app.
jpackage \
    --name Luminara \
    --app-version "$RELEASE_VERSION" \
    --runtime-image GUI/target/LuminaraRuntime \
    --type app-image \
    --module luminara.gui/luminara_gui.RunApp \
    --icon "$ICON" \
    --dest GUI/target/installer

cd ./GUI/target/installer

# Zip/Tar app.
if [[ "$OS" == "windows" ]]; then
  ARCH_NAME="Luminara-${OS}-${RELEASE_VERSION}.zip"
  zip -r "$ARCH_NAME" Luminara
else
  ARCH_NAME="Luminara-${OS}-${RELEASE_VERSION}.tar.gz"
  tar -czf "$ARCH_NAME" Luminara
fi

echo "build_path=GUI/target/installer/$ARCH_NAME" >> $GITHUB_ENV
echo "build_name=Luminara-${OS}-${RELEASE_VERSION}" >> $GITHUB_ENV
