package com.github.appocalypse;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Installer {
	final static private Logger LOG = LoggerFactory.getLogger(Installer.class);
	
	public static void main(String[] args) throws Exception {
		if (args.length != 5) {
			LOG.error("Please pass in <jdkMajorVersion> <jdkMinorVersion> <jdkBuildNumber> <osVersion> <outputDirectory>");
			return;
		}
		
		// Example: 7,65,17,linux-i586,.
		final String jdkMajorVersion = args[0];
		final String jdkMinorVersion = args[1];
		final String jdkBuildNumber = args[2];
		final String osVersion = args[3];
		final File outputDirectory = new File(args[4]);
		
		if (outputDirectory.exists() && outputDirectory.isFile()) {
			LOG.error("{} is not a directory", outputDirectory.getCanonicalPath());
			return;
		}
		
		if (outputDirectory.exists() && !outputDirectory.canWrite()) {
			LOG.error("{} is not writeable", outputDirectory.getCanonicalPath());
			return;
		}
		
		if (!outputDirectory.exists() && !outputDirectory.mkdirs()) {
			LOG.error("Unable to mkdirs {}", outputDirectory.getCanonicalPath());
			return;
		}
		
		final String downloadUrl = String.format("http://download.oracle.com/otn-pub/java/jdk/%su%s-b%s/jdk-%su%s-%s.tar.gz", 
				jdkMajorVersion, jdkMinorVersion, jdkBuildNumber, jdkMajorVersion, jdkMinorVersion, osVersion);
		
		InputStream downloadStream = Request.Get(downloadUrl)
			.addHeader("Cookie", "gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie")
			.execute()
			.returnContent()
			.asStream();
		
		LOG.info("Reading download stream");
		
		GzipCompressorInputStream gzIn = new GzipCompressorInputStream(downloadStream);
		
		TarArchiveInputStream archiveInputStream = new TarArchiveInputStream(gzIn);
		
		for (TarArchiveEntry archiveEntry = archiveInputStream.getNextTarEntry();
				archiveEntry != null; 
				archiveEntry = archiveInputStream.getNextTarEntry()) {
			
			LOG.info(archiveEntry.getName());
			
			if (archiveEntry.isFile()) {
				File file = new File(outputDirectory, archiveEntry.getName());
				file.getParentFile().mkdirs();
				FileUtils.copyInputStreamToFile(new LimitedInputStream(archiveInputStream), file);	
			} 
		}
	}
	
	public static class LimitedInputStream extends FilterInputStream {

		protected LimitedInputStream(InputStream in) {
			super(in);
		}
		
		@Override
		public int read(byte[] b) throws IOException {
			return read(b, 0, b.length);
		}
		
		@Override
		public void close() { }
		
	}
}
