# Interpreter-APO

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
+ `ant rebuild` - resolve dependencies & compile source files & create executable jar
+ `ant refresh` - remove additional build files & resolve dependencies & compile source files &
  create executable jar
+ `ant refresh-main` - remove additional build files & compile source files & create executable jar
  & run all tests
+ `ant refresh-all` - remove additional build files & resolve dependencies & compile source files &
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
