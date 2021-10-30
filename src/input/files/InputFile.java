package input.files;

import java.io.File;
import java.io.FileNotFoundException;

import reader.Parser;

import java.util.Scanner;

import multiverseTour.TourSystem;
public class InputFile {
	public static void loadFile(String path,Parser reader,TourSystem tour) throws FileNotFoundException {
		try {
			Scanner fileScanner=new Scanner(new File(path));
			fileScanner.useDelimiter("\n");
			String line="";
			line=fileScanner.next();
			do {				
				line=fileScanner.next();
				reader.Load(line, tour);
			}while (fileScanner.hasNext());
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("Archivo "+path+" no encontrado\n");
			e.printStackTrace();
			throw e;
		}
	}
}
