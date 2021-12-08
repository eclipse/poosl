# POOSL-to-Java
Example on how to invoke Java code from POOSL.

## How this works

This example spawns a reusable and generic JSON-RPC server over TCP/IP. In the glue layer provided by this example (`com.example.org.Service`), a number of Java functions with different argument and return types are exposed to POOSL.

## Installation
This setup is tested under Java 1.8

1) Ensure Maven 3 is installed.
2) Run `mvn compile exec:java -Dexec.mainClass="com.example.org.Main"`. If started successfully, it will tell you on which port it runs (defaults to 9999).

Alternatively, you can import this project in your favorite editor and run it from there.

## How to (re-)use
All the files reside inside this directory (including `pom.xml`). `com.example.org.Main` can be re-used without modification. If needed, you can change the port number.

Modify `com.example.org.Service` to implement the functionality you want to expose to POOSL. Any Java code can be used to implement functions, but the types of arguments and return types must be JSON serializable, i.e. the Java primitive types string, integer, float, null, possibly contained in a hierarchy of lists and maps.

