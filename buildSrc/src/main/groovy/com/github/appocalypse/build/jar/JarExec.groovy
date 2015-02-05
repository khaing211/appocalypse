package com.github.appocalypse.build.jar

import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

class JarExec {

	@InputFile
	File jarFile
	
	@OutputFile
	File outFile
	
	
	@TaskAction
	void jarToExec() {
		
	}
}
