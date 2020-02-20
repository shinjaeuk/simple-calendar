#!/bin/bash
echo 'Build React Proejct'
cd webapp
npm install
npm run build

echo 'Copy resources'
rm -rf ../src/main/resources/static
cp -R ./build ../src/main/resources/static

echo 'Budil Spring Boot Proejct'
cd ..
gradle build