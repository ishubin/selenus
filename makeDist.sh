set -e

mkdir -p target
rm -rf target/*
mkdir -p dist
rm -rf dist/*
mkdir -p dist/bin
mkdir -p dist/sources

mvn clean install

versionInPom=`cat pom.xml | grep "<version>.*</version>" | head -n 1 | sed 's/[<version> | </version>]//g'`
version=`cat pom.xml | grep "<version>.*</version>" | head -n 1 | sed 's/[<version> | </version> | (SNAPSHOT) | -]//g'`
echo Version is $version

mvn clean
# mvn clean install 
mvn assembly:assembly 
cp target/selenus-$versionInPom.jar dist/bin/selenus-$version.jar 
cp target/selenus-jar-with-dependencies.jar dist/bin/selenus-$version-all-dep.jar 
cp LICENSE-2.0.txt dist/bin/.
cp README dist/bin/.
cp selenusMessages.properties dist/bin/. 
cp experior.properties dist/bin/.
cp ../oculus-experior/defaultMessages.properties dist/bin/.

cd dist/bin
zip -r -9 ../selenus-$version-bin.zip *


cd ../..
cp pom.xml dist/sources/.
cp -r src dist/sources/src
cp LICENSE-2.0.txt dist/sources/.
cp README dist/sources/.
cp selenusMessages.properties dist/sources/. 
cp experior.properties dist/sources/.
cp ../oculus-experior/defaultMessages.properties dist/sources/.

cd dist/sources
zip -r -9 ../selenus-$version-sources.zip *

cd ..
rm -rf bin
rm -rf sources
