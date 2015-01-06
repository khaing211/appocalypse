package com.github.appocalypse.pandemic.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;


public class PandemicScript {
	public void run(InputStream inputStream, OutputStream outputStream) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			 PrintWriter writer = new PrintWriter(outputStream)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.println(line);
			}
			
			writer.flush();
		}
	}
}
