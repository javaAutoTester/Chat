package by.htp.server.run;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import by.htp.server.data.DataHandler;

public class MainServer {
	private static int client_count;

	public static void main(String[] args) {

		ServerSocket server = null;

		try {

			server = new ServerSocket(9595);
			System.out.println("Server started...");
			while (true) {
				Socket client = server.accept();
				client_count++;
				System.out.println("Client " + client_count + " connected...");
				while (true) {
					byte[] data = new byte[1024];
					client.getInputStream().read(data);
					String dataToString = new String(data);
					System.out.println("Data received: " + dataToString);
					//выходим из цикла когда поступает команда "close"
					//в клиенте тоже выходим из цикла. сокет закрывается автоматически
					// в блоке try с ресурсами
					if((dataToString.trim()).equals("close")) {
					break;	
					}else {
					String response = DataHandler.extractData(data);
					client.getOutputStream().write(response.getBytes());
					}
				}
			}
		} catch (SocketException e) {
			System.out.println("Client disconnected");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				server.close();

				System.out.println("Server closed...");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Java русские буквы и не только сид форум ру

	}
}
