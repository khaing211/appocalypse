package com.github.appocalypse.grammars.expr;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

public class ExprTest {

	@Test
	public void test() {
		ANTLRInputStream input = new ANTLRInputStream("3+2");
		ExprLexer exprLexer = new ExprLexer(input);
		CommonTokenStream commonTokenStream = new CommonTokenStream(exprLexer);
		ExprParser exprParser = new ExprParser(commonTokenStream);
	}

}
