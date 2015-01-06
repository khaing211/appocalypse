package com.github.appocalypse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import com.github.appocalypse.pandemic.console.PandemicConsole;
import com.github.appocalypse.pandemic.script.PandemicScript;

public class Pandemic {

	public static void main(String[] args) throws Exception {
		Options options = new Options();
		
		options.addOption("h", "help", false, "print this message");
		options.addOption("i", "interactive", false, "run Pandemic simulator in console interactive mode");
		options.addOption("g", "gui", false, "run Pandemic simulator in GUI mode");
		options.addOption("f", "file", true, "run Pandemic simulator by read game event from file");
		options.addOption("o", "outfile", true, "only valid for script mode either from stdin or file");

		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse( options, args);
		
		if (cmd.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "Pandemic", options );
			return;
		} else if (cmd.hasOption("i")) {
			PandemicConsole console = new PandemicConsole();
			console.run();
			return;
		} else if (cmd.hasOption("f")) {
			FileInputStream inputStream = new FileInputStream(cmd.getOptionValue("f"));
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			
			OutputStream outputStream = System.out;
			if (cmd.hasOption("o")) {
				FileOutputStream fileOutputStream = new FileOutputStream(cmd.getOptionValue("o"));
				outputStream = new BufferedOutputStream(fileOutputStream);
			}
			
			PandemicScript script = new PandemicScript();
			script.run(bufferedInputStream, outputStream);
			return;
		} else if (cmd.hasOption("g")) {
			System.out.println("GUI mode is not supported yet");
			return;
		} else {
			OutputStream outputStream = System.out;
			if (cmd.hasOption("o")) {
				FileOutputStream fileOutputStream = new FileOutputStream(cmd.getOptionValue("o"));
				outputStream = new BufferedOutputStream(fileOutputStream);
			}
			
			PandemicScript script = new PandemicScript();
			script.run(System.in, outputStream);
			return;
		}
	}

}
