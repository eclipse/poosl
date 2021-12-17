/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package com.example.org;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.StreamServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * This class exposes a JSON-RPC 2.0 server via TCP connection. The default port is 9089.
 *
 * It will expose all public method in `com.example.org.Service`, that have JSON compatible arguments.
 * 
 * @author <a href="mailto:benny.akesson@tno.nl">Benny Akesson</a>
 */
public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        // Create the JSON-RPC server.
        JsonRpcServer jsonRpcServer = new JsonRpcServer(service);

        // Create the stream server.
        int maxThreads = 1;
        int port = 9999;

        InetAddress bindAddress = null;
        StreamServer streamServer = null;

        try {
            bindAddress = InetAddress.getByName("localhost");
            streamServer = new StreamServer(jsonRpcServer, maxThreads, new ServerSocket(port, 1, bindAddress));

            // Start it, this method doesn't block
            streamServer.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
