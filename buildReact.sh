#!/usr/bin/env bash

echo "Building react app"
pushd app
npm run build
echo "Copying assets to the Spring Boot app"
cp -R build/* ../src/main/resources/static/
popd