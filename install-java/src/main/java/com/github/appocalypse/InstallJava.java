package com.github.appocalypse;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.fluent.Request;

public class InstallJava {
	public static void main(String[] args) throws Exception {
		//String jdkVersion = args[0];
		//String osVersion = args[1];
		InputStream downloadStream = Request.Get("http://download.oracle.com/otn-pub/java/jdk/7u65-b17/jdk-7u65-linux-i586.tar.gz")
			.addHeader("Cookie", "gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie")
			.execute()
			.returnContent()
			.asStream();
		
		System.out.println("Download completed");
		
		GzipCompressorInputStream gzIn = new GzipCompressorInputStream(downloadStream);
		
		TarArchiveInputStream archiveInputStream = new TarArchiveInputStream(gzIn);
		
		for (TarArchiveEntry archiveEntry = archiveInputStream.getNextTarEntry();
				archiveEntry != null; 
				archiveEntry = archiveInputStream.getNextTarEntry()) {
			
			System.out.println(archiveEntry.getName());
			
			if (!archiveEntry.isDirectory()) {
				File file = new File(archiveEntry.getName());
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
