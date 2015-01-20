package com.github.appocalypse.build.antlr4

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

/**
 * Test Java compile of grammars code against example files
 */
class Antlr4Test extends DefaultTask {
	@Input
	Object antlr4Classpath
	
	@InputFiles
	def exampleFiles
	
	@Input
	@Optional
	def packageName
	
	@Input
	def grammarName
	
	@Input
	@Optional
	def showTrees
	
	@Input
	@Optional
	def verbose
	
	@Input
	def entryPoint
	
	@TaskAction
	void antlr4Test() {
		
	}
}
