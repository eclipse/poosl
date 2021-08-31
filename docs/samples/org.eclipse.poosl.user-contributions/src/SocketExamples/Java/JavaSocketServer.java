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
package SocketExamples.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The JavaSocketServer.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 */
public class JavaSocketServer extends Thread {
    static final int PORT_NUMBER = 9090;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        try {
            String valueAsString;
            while ((valueAsString = in.readLine()) != null) {
                System.out.println("Read: " + valueAsString);
                int valueAsInteger = Integer.parseInt(valueAsString);
                valueAsInteger = valueAsInteger - 1;
                out.println(valueAsInteger);
                System.out.println("Send: " + valueAsInteger);
            }
        } catch (IOException e) {
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
