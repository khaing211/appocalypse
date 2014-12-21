package com.github.appocalypse;

import java.io.InputStreamReader;
import java.util.function.Consumer;

import org.apache.commons.io.LineIterator;
import org.apache.commons.vfs2.FileObject;

import com.github.appocalypse.archivefilesystem.ArchiveFileSystem;

public class Grep {

	public static void main(String[] args) throws Exception {
		final String root = args[0];
		final String keyword = args[1];
		
		ArchiveFileSystem.transverse(root, new Consumer<FileObject>() {
			@Override
			public void accept(FileObject file) {
				try {
					if (file.isReadable() && file.getType().hasContent()) {
						LineIterator lineIterator = new LineIterator(new InputStreamReader(file.getContent().getInputStream()));
						
						boolean outputFileName = false;
						int lineNumber = 0;
						
						while (lineIterator.hasNext()) {
							String line = lineIterator.next();
							
							if (line.contains(keyword)) {
								if (!outputFileName) {
									System.out.println(ArchiveFileSystem.absolutePath(file) + ":");
									outputFileName = true;
								}
								
								System.out.println(lineNumber + ": " + line);
							}
							
							lineNumber++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
	}

}
