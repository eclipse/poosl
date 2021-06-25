package SocketExamples.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
