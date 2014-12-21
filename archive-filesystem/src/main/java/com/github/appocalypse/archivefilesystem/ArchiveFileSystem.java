package com.github.appocalypse.archivefilesystem;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.provider.LayeredFileName;

public class ArchiveFileSystem {
	
	public static FileObject resolve(String path) 
			throws FileSystemException, FileNotFoundException {
		
		final FileSystemManager fileSystemManager = VFS.getManager();
		return resolve(fileSystemManager, path);
	}
	
	public static FileObject resolve(final FileSystemManager fileSystemManager, String path) 
			throws FileNotFoundException, FileSystemException {
		
		final LinkedList<String> paths = resolvePath(path);
		
		FileObject currentFile = null;
		final Iterator<String> iterPaths = paths.descendingIterator();
		
		while (iterPaths.hasNext()) {
			String name = iterPaths.next();
			
			if (currentFile == null) {
				if ("..".equals(name)) {
					name = Paths.get("..").toAbsolutePath().normalize().toString();
				}
				
				currentFile = fileSystemManager.resolveFile("file://" + name);
			} else {
				if (currentFile.isReadable() && currentFile.getType().hasChildren()) {
					currentFile = fileSystemManager.resolveFile(currentFile, name);
				} else {
					throw new FileNotFoundException(currentFile.getName().getFriendlyURI() + "/" + name + " is not exist");
				}
			}
			
			if (!currentFile.exists()) {
				throw new FileNotFoundException(currentFile.getName().getFriendlyURI() + " is not exist");
			}
			
			currentFile = layeredFileSystem(fileSystemManager, currentFile);
		}
		
		return currentFile;
	}
	
	public static void transverse(String path, Consumer<FileObject> consumer) 
			throws FileNotFoundException, FileSystemException {
		
		final FileSystemManager fileSystemManager = VFS.getManager();
		transverse(fileSystemManager, path, consumer);
	}
	
	public static void transverse(FileSystemManager fileSystemManager, 
			String path, Consumer<FileObject> consumer) 
			throws FileSystemException, FileNotFoundException {
		
		FileObject root = resolve(fileSystemManager, path);
		consumer.accept(root);
		
		tranverse(fileSystemManager, root, consumer);
	}
	
	public static String absolutePath(FileObject file) {
		FileName fileName = file.getName();
		LinkedList<String> parts = new LinkedList<String>();
		
		while (fileName != null) {
			parts.add(fileName.getPath());
			
			if (fileName instanceof LayeredFileName) {
				fileName = ((LayeredFileName)fileName).getOuterName();
			} else {
				fileName = null;
			}
		}
		
		StringBuilder builder = new StringBuilder();
		Iterator<String> concatIter = parts.descendingIterator();
		
		while (concatIter.hasNext()) {
			builder.append(concatIter.next());
		}
		
		return builder.toString();
	}
	
	private static void tranverse(final FileSystemManager fileSystemManager, final FileObject root, 
			Consumer<FileObject> consumer) throws FileSystemException {
		
		for (FileObject file : root.getChildren()) {
			file = layeredFileSystem(fileSystemManager, file);
			
			consumer.accept(file);
			
			if (file.isReadable() && file.getType().hasChildren()) {
				tranverse(fileSystemManager, file, consumer);
			}
		}
	}
	
	private static LinkedList<String> resolvePath(String path) {
		final LinkedList<String> part = new LinkedList<String>();
		String childPath = null;
		
		for (;!path.equals(childPath) && path.isEmpty() == false;
			 childPath = path,
			 path = FilenameUtils.getFullPathNoEndSeparator(path)) {
			
			String name = FilenameUtils.getName(path);
			part.add(name);
		}
		
		// adding root path or last relative path
		if (!part.isEmpty()) part.removeLast();
		part.add(childPath);
		
		// remove all empty part i.e. '/'
		part.removeIf(String::isEmpty);
		
		return part;
	}
	
	private static FileObject layeredFileSystem(FileSystemManager fileSystemManager, FileObject file) throws FileSystemException {
		if (fileSystemManager.canCreateFileSystem(file)) {
			return fileSystemManager.createFileSystem(file);
		} else {
			return file;
		}
	}
}
