# Interpreter-APO

[![GitHub Actions](https://github.com/ref-humbold/Interpreter-APO/actions/workflows/build-and-test.yml/badge.svg)](https://github.com/ref-humbold/Interpreter-APO/actions/workflows/build-and-test.yml)

![Release](https://img.shields.io/github/v/release/ref-humbold/Interpreter-APO?style=plastic)
![License](https://img.shields.io/github/license/ref-humbold/Interpreter-APO?style=plastic)

Interpreter of a fictional assembly-like APO language

APO language files should have file extension `.apo`.

-----

## System requirements

> versions used by the author are in italics

+ Operating system \
  *Debian testing*
+ [Java](https://www.oracle.com/technetwork/java/javase/overview/index.html) \
  *APT package `openjdk-21-jdk`, version 21 SE*
+ [Gradle](https://gradle.org/) \
  *SDK-Man `gradle`, version 8.14.3*

## Dependencies

> automatically downloaded during build process

+ Apache Commons CLI 1.8.+

## Test Dependencies

> automatically downloaded during build process

+ JUnit 5.+
+ AssertJ 3.+

-----

## How to build?

Interpreter-APO can be built with **Gradle**. All dependencies are downloaded during build, so
make sure your Internet connection is working!

Possible Gradle tasks are:

+ `gradle build` - resolve dependencies & compile source files & create jar & run all tests
+ `gradle jar` - resolve dependencies & compile source files & create jar
+ `gradle test` - run all tests
+ `gradle javadoc` - generate Javadoc
+ `gradle rebuild` - remove additional build files & resolve dependencies & compile source files &
  create jar & run all tests

## How to run?

Interpreter-APO can be run by the executable *sh* script:

```sh
$ sh /path-to-project-directory/build/scripts/apolang file.apo
```

Alternatively one may directly execute the *jar* file:

```sh
$ java -jar /path-to-project-directory/build/libs/APOlang-{version}.jar file.apo
```
