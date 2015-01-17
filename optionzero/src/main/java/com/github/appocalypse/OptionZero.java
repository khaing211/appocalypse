package com.github.appocalypse;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import com.github.appocalypse.optionzero.GoogleService;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListThreadsResponse;
import com.google.api.services.gmail.model.Thread;

public class OptionZero {
	
	// Email address of the user, or "me" can be used to represent the currently
	// authorized user.
	private static final String USER = "me";

	public static CommandLine usage(String[] args) throws ParseException {
		@SuppressWarnings("static-access")
		Option clientSecretPath = OptionBuilder
				.withLongOpt("client-secret")
				.withDescription("Path to client secret json file")
				.hasArg()
				.isRequired()
				.create("c");
		
		@SuppressWarnings("static-access")
		Option username = OptionBuilder
				.withLongOpt("username")
				.withDescription("google email address of the user")
				.hasArg()
				.isRequired()
				.create("u");
		
		@SuppressWarnings("static-access")
		Option password = OptionBuilder
				.withLongOpt("password")
				.withDescription("password to google email address")
				.hasArg()
				.isRequired()
				.create("p");
		
		Options options = new Options();
		options.addOption("h", "help", false, "print this message");
		options.addOption(clientSecretPath);
		options.addOption(username);
		options.addOption(password);
		
		
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = parser.parse( options, args);
		
		if (cmd.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp( "OptionZero", options );
			System.exit(0);
		}
		
		return cmd;
	}
	
	public static void main(String[] args) throws Exception {
		final CommandLine cmd = usage(args);

		final String clientSecretPath = cmd.getOptionValue("c");
		final String username = cmd.getOptionValue("u");
		final String password = cmd.getOptionValue("p");
		
		// Create a new authorized Gmail API client
		Gmail service = new GoogleService().connectToGmail(clientSecretPath, username, password);
		
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
