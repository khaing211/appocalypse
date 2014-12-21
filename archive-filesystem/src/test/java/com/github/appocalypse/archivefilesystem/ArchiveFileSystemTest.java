package com.github.appocalypse.archivefilesystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArchiveFileSystemTest {

	@Test
	public void testAbsolutePath() throws Exception {
//		ArchiveFileSystem.resolve("/");
		ArchiveFileSystem.transverse("/tmp", file -> System.out.println(file.getName().getFriendlyURI()));
		ArchiveFileSystem.resolve("/tmp");
//		ArchiveFileSystem.read("a");
//		ArchiveFileSystem.read("/a/b");
//		ArchiveFileSystem.read("a/b");
//		ArchiveFileSystem.read("/a/b/");
//		ArchiveFileSystem.resolve("a/b/");
//		ArchiveFileSystem.resolve("a//b/");
		

	}
	
	/*
	@Test
	public void testRelativePath() throws Exception {
		ArchiveFileSystem.read("a/b");
	}
	
	@Test
	public void testDirectory() throws Exception {
		ArchiveFileSystem.read("a/b/");
	}
	
	@Test
	public void testFile() throws Exception {
		ArchiveFileSystem.read("a");
	}
	*/
}
