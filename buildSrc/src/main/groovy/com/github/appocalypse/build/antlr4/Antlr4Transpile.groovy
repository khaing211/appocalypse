package com.github.appocalypse.build.antlr4

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.SkipWhenEmpty
import org.gradle.api.tasks.TaskAction

class Antlr4Transpile extends DefaultTask {

	@Input
	@SkipWhenEmpty
	FileCollection source
	
	@Input
	Object antlr4Classpath
	
	@Input
	@Optional
	String packageNamespace	
	
	@Input
	@Optional
	boolean noListener = false
	
	@Input
	@Optional
	boolean noVisitor = false
	
	@Input
	@Optional
	boolean Werror = false
	
	@TaskAction
	void transpile() {
		getProject().javaexec {
			classpath = antlr4Classpath
			main = 'org.antlr.v4.Tool'
		}
		
	}


}
