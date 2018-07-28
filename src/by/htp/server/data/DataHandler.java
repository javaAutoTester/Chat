package by.htp.server.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataHandler {
	
	public static String extractData(byte[] data) {
		String[] dataSet = new String(data).split(",");
//		System.out.println("extractData "+dataSet[0]);
//		System.out.println("extractData "+dataSet[1]);
		String result = "Request is not correct\n";
		if((dataSet[0].trim()).equals("getBook")) {
			result = extractBook(dataSet[1].trim());
		}
		return result;
	}
    
	private static ArrayList<String[]> readDataFromFile(String file_name) {
		File f = new File(file_name);
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ArrayList<String[]> dataArray = new ArrayList<>();
		try(Scanner scanner = new Scanner(f);) {
			while(scanner.hasNextLine()) {
//				System.out.println(scanner.nextLine());
				dataArray.add(scanner.nextLine().split(","));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (String[] strings : dataArray) {
//			System.out.println("После сканнера% "+strings[0]+""+strings[1]);
//		}
		return dataArray;
	}
	
	private static String extractBook(String book_id ) {
		String filePath = "/home/papa/Programs/Max-workspace/ConsoleServer/bookFile.txt";
		ArrayList<String[]> book_array = readDataFromFile(filePath);
		String result = "Book not found\n";
		for (int i = 0; i < book_array.size(); i++) {
			if(book_id.equals(book_array.get(i)[0])) {
				result ="You requested: "+ book_array.get(i)[0]+" "+book_array.get(i)[1]+"\n";
				
			}
		}
		
		return result;
		
	}
	
}