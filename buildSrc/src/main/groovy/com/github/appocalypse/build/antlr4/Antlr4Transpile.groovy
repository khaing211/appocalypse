package com.github.appocalypse.build.antlr4

import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction


class Antlr4Transpile extends SourceTask {
	
	@InputFiles
	FileCollection antlr4Classpath
	
	@Input
	@Optional
	String javaPackage
	
	@Input
	@Optional
	boolean generateListener = false
	
	@Input
	@Optional
	boolean generateVisitor = false
	
	@Input
	@Optional
	boolean treatWarningAsError = false
	
	@Input
	@Optional
	boolean showLongMessages = false
	
	@Input
	@Optional
	String grammarFileEncoding
	
	@OutputDirectory
	File destinationDir = getTemporaryDir()
	
	Antlr4Transpile() {
		include('**/*.g4')	
	}
	
	@TaskAction
	void transpile() {
		final def destinationDirPath = getDestinationDir().canonicalPath
		final def packagePath = javaPackage ? javaPackage.replaceAll("\\.", "/") : ""
		final def classPath = "${destinationDirPath}/${packagePath}"
		
		final def antlr4Args = []
		
		if (generateListener) {
			antlr4Args << '-listener'
		} else {
			antlr4Args << '-no-listener'
		}
		
		if (generateVisitor) {
			antlr4Args << '-visitor'
		} else {
			antlr4Args << '-no-visitor'
		}
		
		if (treatWarningAsError) {
			antlr4Args << '-Werror'
		}
		
		if (showLongMessages) {
			antlr4Args << '-long-messages'
		}
		
		if (grammarFileEncoding) {
			antlr4Args << ['-encoding', grammarFileEncoding]
		}
		
		antlr4Args << ['-o', classPath]
		
		if (javaPackage) {
			antlr4Args << ['-package', javaPackage]
		}
		
		antlr4Args << getSource().files
		
		getProject().javaexec {
			classpath = antlr4Classpath
			main = 'org.antlr.v4.Tool'
			
			args = antlr4Args.flatten()
		}		
	}
}
