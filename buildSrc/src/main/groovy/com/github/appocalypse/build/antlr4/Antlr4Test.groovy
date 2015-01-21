package com.github.appocalypse.build.antlr4

import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction

/**
 * Test Java compile of grammars code against example files
 */
class Antlr4Test extends SourceTask {
	@InputFiles
	FileCollection antlr4Classpath
	
	@Input
	@Optional
	String javaPackage
	
	@Input
	def grammarName
	
	@Input
	@Optional
	def showTree
	
	@Input
	def entryPoint
	
	@TaskAction
	void antlr4Test() {
		final String namespace = null != javaPackage ? javaPackage + "." + grammarName : grammarName
		
		final String lexerClassName = namespace + "Lexer"
		final String parserClassName = namespace + "Parser"
		
		logger.info("Lexer classname: [{}]", lexerClassName)
		logger.info("Parser classname: [{}]", parserClassName)
		
		ClassLoader classLoader = getClassLoader()
		Class<?> lexerClass = classLoader.loadClass(lexerClassName)
		Class<?> parserClass = classLoader.loadClass(parserClassName)
		Class<?> antlrFileStreamClass = classLoader.loadClass('org.antlr.v4.runtime.ANTLRFileStream')
		Class<?> commonsTokenStreamClass = classLoader.loadClass('org.antlr.v4.runtime.CommonTokenStream')
		Class<?> bailErrorStrategyClass = classLoader.loadClass('org.antlr.v4.runtime.BailErrorStrategy')
		Class<?> treesClass = classLoader.loadClass('org.antlr.v4.runtime.tree.Trees')
		
		getSource().files.each {
			logger.info("Parsing file: [{}]", it)
			
			def antlrFileStream = antlrFileStreamClass.newInstance(it.getAbsolutePath(), "UTF-8")
			def lexer = lexerClass.newInstance(antlrFileStream)
			def tokens = commonsTokenStreamClass.newInstance(lexer)
			
			tokens.getTokens().each { logger.info("Token: [{}]", it) }
			
			def parser = parserClass.newInstance(tokens)
			parser.setErrorHandler(bailErrorStrategyClass.newInstance())
			
			def parserRuleContext = parser."$entryPoint"()
			
			if (showTree) {
				def String lispTree = treesClass.'toStringTree'(parserRuleContext, parser)
				logger.info(lispTree)
			}
		}
	}
	
	private ClassLoader getClassLoader() {
		logger.info("Antlr4 classpath: [{}]", antlr4Classpath.getFiles())
		final List<URI> uris = antlr4Classpath.getFiles().collect { it.toURI() }
		final URL[] urls = uris.collect { it.toURL() } as URL[]
		logger.info("Antlr4 classpath url for classloader: [{}]", Arrays.toString(urls))
		return new URLClassLoader(urls, Thread.currentThread().getContextClassLoader())
	 }
}
