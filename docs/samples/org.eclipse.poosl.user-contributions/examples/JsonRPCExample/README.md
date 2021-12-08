# JSON-RPC Example

This example shows how to call methods written in Python or Java from POOSL using remote procedure calls (RPC) based on JSON-RPC.

## Introduction

This example demonstrates a simple and re-usable approach to invoke functionality implemented in either Python or Java. This enables parts of the functionality used in POOSL simulations to be implemented in these languages and benefit from their vast standard libraries and third-party packages. POOSL has support for sending JSON messages over a SocketProcess (TCP connection). This functionality is extended with support for [JSON-RPC](http://www.jsonrpc.org/specification), which is a stateless, light-weight remote procedure call (RPC) protocol that facilitates communication with a JSON-RPC server.

The Python and Java parts of this example spawns a reusable and generic JSON-RPC server over TCP/IP. In addition, a so-called 'glue' layer is used to expose certain methods to POOSL.
In the POOSL part, JSON-RPC messages are exchanged using the Request and Response data classes from the JSON-RPC POOSL implementation. The example POOSL code shows how to make remote procedure calls to a JSON-RPC server and print the results to the console.

## How to run

1) Go to the Python or Java directory and start a JSON-RPC server, following the instructions in the file README.md in that directory.
2) Start the POOSL IDE and simulate `model.poosl` located in the POOSL directory. When you simulate this program, you will see that it prints the results of the RPC calls to the console.
