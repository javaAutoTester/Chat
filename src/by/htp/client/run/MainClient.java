package by.htp.client.run;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import by.htp.client.console.ConsoleClient;
import by.htp.client.console.FailedConnectiotExeption;
import by.htp.client.entity.Book;

public class MainClient {

	public static void main(String[] args) {
		ConsoleClient client = new ConsoleClient();
		String info = "Enter request";
		try(Socket socket = client.connect("localhost", 9595)) {
//			socket.setKeepAlive(true);
			Scanner scanner = new Scanner(System.in);
			do {
				System.out.println("Enter your request:");
				info = scanner.nextLine();
				
				if(!(info.trim().equals("close"))) {
					socket.getOutputStream().write(info.getBytes());
					byte[] b = new byte[1024];
					socket.getInputStream().read(b);
					System.out.println(new String(b));
						}
				if(info.trim().equals("close")) {
					socket.getOutputStream().write(info.getBytes());
					System.out.println("Connection closed");
				}
					
			} while (!(info.trim().equals("close")));

			// Book book = client.getBook(3);
		} catch (FailedConnectiotExeption e) {
			System.out.println("You can not connect to the server");
			e.printStackTrace();
		} catch (SocketException e) {
			System.out.println("Client disconnected");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
