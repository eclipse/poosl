[![gh-actions status](https://github.com/ObeoNetwork/POOSL/workflows/Java%20CI/badge.svg)](https://github.com/ObeoNetwork/POOSL/actions/workflows/maven.yml)


# Brief introduction to the POOSL project

## How to get the product

Update site and help are accessible on <a 
href="https://obeonetwork.github.io/POOSL" >gh-pages site</a>.

Products are available in github release. __TBD__

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



## Automatic Tests via RCPTT (__Currently under refactoring__)

We have different ways of running the tests

- Running in a _Jenkins_ environment
- Running as command line tool
- Running with _maven_
- Running with the _rcptt ide_

All the needed tests and scripts are available at __${POOSL_SRC}\RCPTT__. 

### Preconditions

- jdk 11 is available
- Only if running the scripts (not via maven)
    - rcptt test runner is available at __C:\opt\rcptt\rcptt.runner-2.5.0__
- eclipse runtime is available at __C:\opt\eclipse\eclipse-2019-12\eclipse__ and setup properly


### Running tests

To run the tests in a _Jenkins_ environment or as command line you will find the proper scripts in the poosl repository.

#### The generic workflow to run the scripts are:

1. Checkout the poosl source+scripts into __${MY_FOLDER}/poosl__
1. Build the plugins as descript above.
1. We can assume the build plugin artifacts are located in __${MY_FOLDER}/poosl/org.eclipse.poosl.update/target/repository__, which is the case after you do the build.
1. Run one of the 
    * scripts: ``{MY_FOLDER}/poosl/RCPTT/runnerScripts/*.cmd``
    * maven: ``mvn clean package -f __${MY_FOLDER}/poosl/RCPTT/runnerScripts/pom-*.xml``
1. Check the results
    * scripts results in __${MY_FOLDER}/result__
    * maven results in __${MY_FOLDER}/rcptt-target__

#### Running with Jenkins

The scripts are developed to run within _Jenkins_ out of the box.

You can follow the generic running tests instructions above where __${MY_FOLDER}__ is automtically set to _Jenkins workspace_

#### Running as Command

To run the test as command line you can follow the generic workflow described above.



