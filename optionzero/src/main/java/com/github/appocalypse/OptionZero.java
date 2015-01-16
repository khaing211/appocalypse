package com.github.appocalypse;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import com.github.appocalypse.optionzero.GoogleService;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;

public class OptionZero {
	
	// Email address of the user, or "me" can be used to represent the currently
	// authorized user.
	private static final String USER = "me";

	public static void main(String[] args) throws Exception {
		Options options = new Options();
		
		options.addOption("h", "help", false, "print this message");

		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse( options, args);
		
		if (cmd.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "OptionZero", options );
			return;
		}
		

		final String clientSecretPath = args[0];
		
		// Create a new authorized Gmail API client
		Gmail service = new GoogleService().connectToGmail(clientSecretPath, "user", "pass");
		
		// Retrieve a page of Threads; max of 100 by default.
		ListThreadsResponse threadsResponse = service.users().threads()
				.list(USER).execute();
		List<Thread> threads = threadsResponse.getThreads();

		// Print ID of each Thread.
		for (Thread thread : threads) {
			System.out.println("Thread ID: " + thread.getId());
			System.out.println(thread.toPrettyString());
			System.out.println("================================");
		}
	}
}
