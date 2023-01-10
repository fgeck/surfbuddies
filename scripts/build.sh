#!/usr/bin/env bash

set -e

FRONTEND_DIR="../src/frontend/"
SRC_DIR="../"
FINAL_BUILD_DIR="../build/"

# build frontend
pushd $FRONTEND_DIR
npm run build
popd

# build backend
pushd $SRC_DIR
./gradlew clean
./gradlew build
popd

# copy built frontend
cp -Rf $FRONTEND_DIR/dist/** $FINAL_BUILD_DIR/resources/main/static/

