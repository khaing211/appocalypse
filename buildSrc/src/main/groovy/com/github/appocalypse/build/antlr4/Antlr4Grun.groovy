package com.github.appocalypse.build.antlr4

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFiles;
import org.gradle.api.tasks.SourceTask;
import org.gradle.api.tasks.TaskAction;

class Antlr4Grun extends SourceTask {
	
	@InputFiles
	FileCollection antlr4Classpath
	
	@Input
	def grammarName
	
	@Input
	def entryPoint
	
	@TaskAction
	void grun() {
		getSource().files.each {
			final def antlr4Args = [grammarName, entryPoint, '-gui', it.canonicalPath ]
			
			getProject().javaexec {
				classpath = antlr4Classpath
				main = 'org.antlr.v4.runtime.misc.TestRig'
				
				args = antlr4Args.flatten()
			}
		}
	}
}
