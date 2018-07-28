package by.htp.client.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import by.htp.client.entity.Book;

public class ConsoleClient {
	private String title;
	private String type;
	private Socket socket;

//	public void connect(String host, int port) throws FailedConnectiotExeption {
//		try {
//			 socket = new Socket(host, port);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// throw new FailedConnectiotExeption(e);
//			throw new FailedConnectiotExeption("Can not connect", e);
//		}
//	}
	
	public Socket connect(String host, int port) throws FailedConnectiotExeption {
	try {
		 socket = new Socket(host, port);
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// throw new FailedConnectiotExeption(e);
		throw new FailedConnectiotExeption("Can not connect", e);
	}
	return socket;
}

	public Book getBook(int id) {
		sendBookRequest(id);
		Book book = recieveBookResponse();

		return book;
	}

	private void sendBookRequest(int id) {
		try{
			OutputStream os = socket.getOutputStream();
			os.write(("getBook"+","+id).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Book recieveBookResponse() {

		Book book = new Book();
		try{	
			InputStream is = socket.getInputStream();
			byte[] data = new byte[1024];
			is.read(data);
System.out.println(data);
//			book.setTitle(new String(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

}
