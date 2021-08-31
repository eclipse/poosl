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
import java.net.Socket;

/**
 * The JavaSocketClient.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class JavaSocketClient extends Thread {
    static final String HOST_NAME = "127.0.0.1"; //$NON-NLS-1$

    static final int PORT_NUMBER = 9090;

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(HOST_NAME, PORT_NUMBER);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        try {
            int valueAsInteger = 4;
            while (0 < valueAsInteger) {
                out.println(valueAsInteger);
                System.out.println("Send: " + valueAsInteger);

                String valueAsString = in.readLine();
                System.out.println("Read: " + valueAsInteger);
                valueAsInteger = Integer.parseInt(valueAsString);
            }
        } catch (IOException e) {
        }

        in.close();
        out.close();
        clientSocket.close();
    }
}
