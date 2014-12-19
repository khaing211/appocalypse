package com.github.appocalypse.build.antlr4

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

class Antlr4Grun extends DefaultTask {
	
	@Input
	Object antlr4Classpath
	
	@TaskAction
	void grun() {
		getProject().javaexec {
			classpath = antlr4Classpath
			main = 'org.antlr.v4.runtime.misc.TestRig'
			
			args = antlr4Args.flatten()
		}
	}
}
