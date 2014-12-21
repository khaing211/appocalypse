package com.github.appocalypse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs2.FileObject;

import com.github.appocalypse.archivefilesystem.ArchiveFileSystem;

public class Cat {
	public static void main(String[] args) throws Exception {
		FileObject file = ArchiveFileSystem.resolve(args[0]);
		if (file.getType().hasContent()) {
			IOUtils.copyLarge(file.getContent().getInputStream(), System.out);
		} else {
			System.err.println(ArchiveFileSystem.absolutePath(file) + " cannot have data content");
		}
	}
}
