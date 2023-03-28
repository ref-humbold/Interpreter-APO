# Interpreter-APO

![GitHub Actions](https://github.com/ref-humbold/Interpreter-APO/workflows/GitHub%20Actions/badge.svg?branch=master)
[![CircleCI](https://circleci.com/gh/ref-humbold/Interpreter-APO/tree/master.svg?style=shield)](https://circleci.com/gh/ref-humbold/Interpreter-APO/tree/master)

![Release](https://img.shields.io/github/v/release/ref-humbold/Interpreter-APO?style=plastic)
![License](https://img.shields.io/github/license/ref-humbold/Interpreter-APO?style=plastic)

Interpreter of a fictional assembly-like APO language

-----

## Dependencies

### Standard build & run

> *versions used by the author are in double parentheses and italic*

General:

+ Operating system \
  *((Debian testing))*
+ [Java](https://www.oracle.com/technetwork/java/javase/overview/index.html) \
  *((APT package `openjdk-11-jdk`, version 11 SE))*
+ [Apache ANT](http://ant.apache.org/) \
  *((APT package `ant`, version 1.10.+))*

### Unit testing

> libraries are automatically downloaded during build process

+ JUnit 5.+
+ AssertJ 3.+

-----

## How to build?

Interpreter-APO can be built with **Apache ANT** using **Apache Ivy** to resolve all dependencies.
Ivy and all libraries are downloaded during build, so make sure your Internet connection is working!

Possible ANT targets are:

+ `ant`, `ant all` - resolve dependencies & compile source files & create executable jar & run all
  tests
+ `ant build` - compile source files & create executable jar
+ `ant main` - compile source files & create executable jar & run all tests
+ `ant test` - run all tests
+ `ant docs` - generate Javadoc
+ `ant rebuild` - remove additional build files & compile source files & create executable jar
+ `ant rebuild-main` - remove additional build files & compile source files & create executable jar
  & run all tests
+ `ant rebuild-all` - remove additional build files & resolve dependencies & compile source files &
  create executable jar & run all tests

## How to run?

Interpreter-APO can be run by the executable *sh* script in the `dist` directory:

```sh
$ sh /path-to-project-directory/dist/apolang
```

Alternatively one may directly execute the *jar* file in the `dist` directory:

```sh
$ java -jar /path-to-project-directory/dist/apolang-{version}.jar
```
