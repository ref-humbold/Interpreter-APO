# Interpreter-APO

![GitHub Actions](https://github.com/ref-humbold/Interpreter-APO/workflows/GitHub%20Actions/badge.svg?branch=master)
[![CircleCI](https://circleci.com/gh/ref-humbold/Interpreter-APO/tree/master.svg?style=shield)](https://circleci.com/gh/ref-humbold/Interpreter-APO/tree/master)

![Release](https://img.shields.io/github/v/release/ref-humbold/Interpreter-APO?style=plastic)
![License](https://img.shields.io/github/license/ref-humbold/Interpreter-APO?style=plastic)

Interpreter of a fictional assembly-like APO language

-----

## System requirements

> versions used by the author are in italics

+ Operating system \
  *Debian testing*
+ [Java](https://www.oracle.com/technetwork/java/javase/overview/index.html) \
  *APT package `openjdk-17-jdk`, version 17 SE*
+ [Apache ANT](http://ant.apache.org/) \
  *APT package `ant`, version 1.10.+*

## Dependencies

> dependencies are automatically downloaded during build process

+ Apache Commons CLI 1.6.+
+ JUnit 5.+
+ AssertJ 3.+

-----

## How to build?

Interpreter-APO can be built with **Apache ANT** using **Apache Ivy** to resolve all dependencies.
Ivy itself and all dependencies are downloaded during build, so make sure your Internet
connection is working!

Possible ANT targets are:

+ `ant`, `ant build` - resolve dependencies & compile source files & create executable jar & run
  all tests
+ `ant resolve` - resolve dependencies
+ `ant jar` - compile source files & create executable jar
+ `ant test` - run all tests
+ `ant docs` - generate Javadoc
+ `ant clean` - remove additional build files
+ `ant rebuild` - remove additional build files & resolve dependencies & compile source files &
  create executable jar & run all tests

## How to run?

Interpreter-APO can be run by the executable *sh* script in the `antBuild` directory:

```sh
$ sh /path-to-project-directory/antBuild/apolang
```

Alternatively one may directly execute the *jar* file in the `antBuild/dist` directory:

```sh
$ java -jar /path-to-project-directory/antBuild/dist/apolang-{version}.jar
```
