package com.github.appocalypse.build.antlr4

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction;

/**
 * Test Java compile of grammars code against example files
 */
class Antlr4Test extends DefaultTask {
	@InputFiles
	def exampleFiles
	
	@TaskAction
	void antlr4Test() {
		
	}
}
