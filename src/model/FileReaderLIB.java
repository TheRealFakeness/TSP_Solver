package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderLIB {

	public static void main(String[] args) throws IOException {
		
		String file = "data" + File.separator + "file.txt";

		BufferedReader br = new BufferedReader(new FileReader(new File(file)));

		String line = null;

		for (int i = 0; i < 8; i++) {
			line = br.readLine();
		}

		while (line != null) {

			// split()
			// trim()
			//subString()

			String[] input = line.split(",");

			line = br.readLine();
		}
		
	}

}
