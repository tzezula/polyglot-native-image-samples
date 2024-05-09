# Native Image Embedding Examples

This project contains examples for embedding a native image using the polyglot API into polyglot applications.

## Overview

The project consists of four sub-projects:

1. **lib**: This sub-project contains the native-image library, which utilizes the [Mustache template engine](https://github.com/spullara/mustache.java) to generate HTML code for a list of Person objects. The Mustache templates are parsed and initialized during the native image build time. The HTML generating code is used without any initialization or template parsing.

2. **js**: In this sub-project, the native-image library is used from JavaScript code.

3. **espresso**: Here, the native-image library is used from the guest Java code.

4. **embedder**: This sub-project demonstrates how the native-image library is used from the host Java code using a polyglot API.

Each sub-project provides a distinct example of embedding a native image within a polyglot application, showcasing different language integrations.

## Usage

First, you need to set the path to your local GraalVM 24.1.0 SNAPSHOT repository in the [parent pom.xml](https://github.com/tzezula/polyglot-native-image-samples/blob/main/pom.xml#L19).

To build the native-image library, navigate to the `lib` sub-project directory and execute:
```
 mvn package
```

To build and run each example, navigate to the respective sub-project directory and execute:
```
mvn package exec:exec
```
