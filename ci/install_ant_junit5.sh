#! /bin/sh

set -e

echo ">>> Check ant version"
ANT=$(ant -version | sed -r 's/.*version ([0-9.]+).*/\1/g')

if [ -f ${HOME}/.ant/lib/ant-junitlauncher-${ANT}.jar ]
then
    echo ">>> File ant-junitlauncher.jar found :)"
else
    LINK="https://repo1.maven.org/maven2/org/apache/ant/ant-junitlauncher/${ANT}/ant-junitlauncher-${ANT}.jar"

    rm -f ${HOME}/.ant/lib/ant-junitlauncher-*.jar
    echo ">>> Download ant-junitlauncher.jar for version ${ANT}"
    wget $LINK -P "${HOME}/.ant/lib"
fi
