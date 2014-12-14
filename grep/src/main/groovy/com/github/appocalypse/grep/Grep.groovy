import groovy.transform.TupleConstructor

import org.apache.commons.vfs2.FileName
import org.apache.commons.vfs2.FileObject
import org.apache.commons.vfs2.FileSystemManager
import org.apache.commons.vfs2.VFS


@TupleConstructor
class Grep {
	final static private FileSystemManager FILE_SYSTEM_MANAGER = VFS.getManager()
	
	final private String keyword
	final private List<String> paths
	
	void printSearchDetails() {
		paths.each { searchPath(it) }
	}
	
	private void searchPath(String path) {
		final def localFile = new File(path)
		final FileObject fileObject = FILE_SYSTEM_MANAGER.toFileObject(localFile)
		
		final def stack = [fileObject] as LinkedList<FileObject>
		
		while (stack.isEmpty() == false) {
			final FileObject currentFileObject = stack.removeLast()
			
			if (currentFileObject.exists() && currentFileObject.isReadable()) {
				if (match(currentFileObject)) {
					print(currentFileObject)
				}
				
				final FileObject layeredFileObject = layeredFileObject(currentFileObject)
				
				if (layeredFileObject.getType().hasChildren()) {
					stack.addAll(layeredFileObject.getChildren())
				}
			}
		}
	}
	
	private boolean match(final FileObject fileObject) {
		final FileName fileName = fileObject.getName()
		
		if (fileName.getPath().matches(keyword)) return true
		if (fileName.getBaseName().matches(keyword)) return true
		if (fileName.getExtension().equalsIgnoreCase(keyword)) return true
		return false
	}
	
	private void print(final FileObject fileObject) {
		final FileName fileName = fileObject.getName()
		printFileDetail(fileObject)
	}
	
	private void printFileDetail(final FileObject fileObject) {
		final FileName fileName = fileObject.getName()
		println fileName.getURI()
	}
	
	private static FileObject layeredFileObject(final FileObject fileObject) {
		if (FILE_SYSTEM_MANAGER.canCreateFileSystem(fileObject)) {
			return FILE_SYSTEM_MANAGER.createFileSystem(fileObject)
		} else {
			return fileObject
		}
	}
	
	static OptionAccessor usage(args) {
		final def cli = new CliBuilder(usage: 'Grep <name> [path/to/jars|directory|file]')
		cli.h( longOpt: 'help', required: false, 'show usage information' )

		//--------------------------------------------------------------------------
		final def opt = cli.parse(args)
		if (!opt) { return opt }
		if (opt.h) {
			cli.usage()
			return
		}
	}
	
	
	static void main(args) {
		def opt = usage(args)
		def arguments = opt.arguments()
		def keyword = arguments.head()
		def paths = arguments.tail()
		new Grep(keyword, paths).printSearchDetails()
	}
}