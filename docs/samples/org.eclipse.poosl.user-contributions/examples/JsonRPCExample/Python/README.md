# POOSL-to-Python
Example on how to invoke Python code from POOSL.

## How this works

This example spawns a reusable and generic JSON-RPC server over TCP/IP. In the glue layer provided by this example (`glue.py`), a number of Python functions with different argument and return types are exposed to POOSL.

## Installation

This setup is tested under Windows 10, Cygwin 2.881 and Ubuntu 17.04 using Python 3.6.

1) Run `pip install -r requirements.txt` to install Python dependencies.
2) Invoke `python Server.py` to start the server.


## How to (re-)use

`Server.py` can be re-used without any modification. Run `python3 Server.py --help` for the command line options describing how to change the host, port, or the name of the Python file whose functionality to expose to POOSL (default is `glue.py`).

Modify `glue.py` to implement the functionality you want to expose to POOSL. Any Python code can be used to implement functions, but the types of arguments and return types must be JSON serializable, i.e. the Python primitive types string, integer, float, null, possibly contained in a hierarchy of lists and maps.
