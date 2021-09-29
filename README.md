[![gh-actions status](https://github.com/ObeoNetwork/POOSL/workflows/Java%20CI/badge.svg)](https://github.com/ObeoNetwork/POOSL/actions/workflows/maven.yml)

This is a temporary repository to prepare POOSL IDE to go to the Eclipse Foundation. Do not refer to this repository as it will soon disappear.

# Brief introduction to the POOSL project

The Parallel Object-Oriented Specification Language (POOSL) is a very expressive language to model 
concurrent hardware/software systems. POOSL has a small set of powerful primitives and it has an 
unambiguous formal semantics in terms of mathematical axioms and rules. POOSL models can be 
simulated with the Rotalumis simulator. 

This project provides an Eclipse-based tool for editing and debugging POOSL models.

## How to install
[Installation guide](https://raw.githubusercontent.com/ObeoNetwork/POOSL/main/docs/User/InstallationManual.pdf) 
helps you to install Eclipse and POOSL features.


## How to download Eclipse Features

Update site and help are accessible on [gh-pages site](https://obeonetwork.github.io/POOSL).


## How to build

Here you will find a introduction how to setup, build and run tests.

### Preconditions

We assume the following tools/frameworks are available

- JDK 11 (JDK 16 or above is not supported for now)
- Maven 3.5+
- rcptt ide: is the IDE for RCPTT development 
- rcptt runner 2.5.0: is the command line tool for RCPTT 
- ${POOSL_SRC} stands for the poosl source location on your host

### Command
To build the poosl project via maven you have to go run the following commands.

```
cd ${POOSL_SRC}
mvn clean verify
```

Executing this command will build the plugins, features, updatesite and products.

### Locations
Update site is available at __${POOSL_SRC}/releng/org.eclipse.poosl.update/target/repository__
Product are available at __${POOSL_SRC}/releng/org.eclipse.poosl.product/target/products__

## Development help

Development guidelines are described at [org.eclipse.poosl.docs/Developer/readme.adoc](https://github.com/ObeoNetwork/POOSL/docs/org.eclipse.poosl.docs/Developer/readme.adoc)

## Automatic Tests via RCPTT

- In Eclipse environment, all tests are grouped in __org.eclipse.poosl.rcptt.allinone/AllTests.suite__

- In bash mode, use maven command:
```
cd ${POOSL_SRC}
mvn verify -P integration-tests
```



