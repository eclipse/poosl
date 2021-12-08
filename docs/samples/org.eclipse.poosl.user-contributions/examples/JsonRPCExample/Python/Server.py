from jsonrpc import JSONRPCResponseManager

try:
    from json import JSONDecodeError
except ImportError:
    JSONDecodeError = ValueError

import socketserver
import argparse
import logging
import json
import sys

# Logger instance.
logger = logging.getLogger(__name__)


class JsonRPCHandler(socketserver.BaseRequestHandler):
    """
    The request handler class for the JSON-RPC server.
    """

    def handle(self):
        """
        Invoked when a connection is accepted. This will process JSON-RPC
        messages as long as there is an active connection.
        """

        logger.info("New connection with %s.", self.client_address[0])

        count = 0

        for request in self.iterate():
            count += 1

            logger.debug("Read request.")
            logger.debug(request)
            response = JSONRPCResponseManager.handle(
                request, self.server.dispatcher)

            if response:
                self.request.sendall(response.json.encode("utf-8") + b"\n")
                logger.debug("Sent response.")
                logger.debug(response.json)

        logger.info(
            "Connection closed with %s, processed %d requests.",
            self.client_address[0], count)

    def iterate(self, count=4096):
        """
        Read `count` bytes from the socket, and process them one byte at a time
        to see when a complete JSON structure is received. If a message is
        incomplete, try it again after new data is received.
        """

        buffer = str()
        start = 0

        while True:
            # Read data from the socket.
            data = self.request.recv(count).decode("utf-8").strip()

            # Process the buffer, one byte at a time.
            buffer += data
            skip = 0

            logger.debug(
                "Read buffer: buffer length=%d, start=%d", len(buffer), start)

            for i in range(start, len(buffer) + 1):
                try:
                    json.loads(buffer[skip:i])

                    yield buffer[skip:i]

                    skip = i
                except JSONDecodeError:
                    continue

            logger.debug("Read buffer: skip=%d", skip)

            # Remove the parts of the buffer that have been read.
            buffer = buffer[skip:]
            start = len(buffer)

            # If no data was received, return. At this point, any data that is
            # still in the buffer will not be processed anymore.
            if not data:
                if buffer:
                    logger.warn(
                        "Connection closed, but read buffer not empty.")
                break


def parse_arguments():
    """
    Parse commandline arguments.
    """

    parser = argparse.ArgumentParser()

    # Add options
    parser.add_argument(
        "--verbose", action="store_true", default=False,
        help="toggle verbose mode")
    parser.add_argument(
        "--host", action="store", type=str, default="localhost",
        help="server host")
    parser.add_argument(
        "--port", action="store", type=int, default=9999,
        help="server port number")
    parser.add_argument(
        "--module", action="store", type=str, default="glue",
        help="module to expose (must be on the Python path)")

    # Parse command line
    return parser.parse_args(), parser


def setup_logging(console=True, verbose=False):
    """
    Setup logging.
    :param bool console: If True, log to console.
    :param bool verbose: Enable debug logging if True.
    """

    # Configure logging
    formatter = logging.Formatter(
        "%(asctime)s - %(name)s - %(levelname)s - %(message)s")
    level = logging.DEBUG if verbose else logging.INFO

    # Add console output handler
    if console:
        console_log_handler = logging.StreamHandler()
        console_log_handler.setLevel(level)
        console_log_handler.setFormatter(formatter)
        logging.getLogger().addHandler(console_log_handler)

    logging.getLogger().setLevel(level)
    logger.info("Verbose level is %d", verbose)


def setup_dispatcher(module_name):
    """
    Import the given module and find all functions to expose them to the
    JSON-RPC server.
    """

    logger.info("Exposing function(s) from module '%s'", module_name)

    module = __import__(module_name, fromlist=module_name.split(".")[-1])

    result = {
        x: y for x, y in module.__dict__.items() if callable(y)
    }

    logger.debug(
        "Found %d function(s) in module: %s",
        len(result), ", ".join(result.keys()))

    return result


def main():
    """
    Application entry point.

    This will startup the JSON-RPC server and serve until the process is
    killed.
    """

    # Parse arguments.
    arguments, parser = parse_arguments()

    # Setup logging.
    setup_logging(verbose=arguments.verbose)

    # Setup the server.
    socketserver.TCPServer.allow_reuse_address = True
    server = socketserver.TCPServer(
        (arguments.host, arguments.port), JsonRPCHandler)
    server.dispatcher = setup_dispatcher(arguments.module)

    # Run the server until CTRL + C is pressed
    server.serve_forever()


# E.g. `python3 Main.py --module glue.py`
if __name__ == "__main__":
    sys.exit(main())
