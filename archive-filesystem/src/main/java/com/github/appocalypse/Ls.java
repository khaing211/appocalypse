package com.github.appocalypse;

import com.github.appocalypse.archivefilesystem.ArchiveFileSystem;

public class Ls {
	public static void main(String[] args) throws Exception {
		ArchiveFileSystem.transverse(args[0], 
				file -> System.out.println(ArchiveFileSystem.absolutePath(file)));
	}
}
