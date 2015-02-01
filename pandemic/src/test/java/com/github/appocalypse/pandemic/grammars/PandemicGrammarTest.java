package com.github.appocalypse.pandemic.grammars;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.grammars.PandemicParser.ActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.BuildActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.CharterFlightActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.CityContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.DirectFlightActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.DiscoverActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.FerryActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.JoinActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.OperationFlightActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.PlayerContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.QuitActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.RoleContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.TreatActionContext;

public class PandemicGrammarTest {

	@Test
	public void test() {
		final String buffer = "discover a cure by discard Ho Chi Minh City , Atlanta , Washington , Chicag";
		
		ANTLRInputStream antlrInputStream = new ANTLRInputStream(buffer);
		
		PandemicLexer lexer = new PandemicLexer(antlrInputStream);
		CommonTokenStream commonsTokenStream = new CommonTokenStream(lexer);
		PandemicParser parser = new PandemicParser(commonsTokenStream);
		parser.addParseListener(new PandemicBaseListener() {

			@Override
			public void visitTerminal(TerminalNode node) {
				System.out.println("visitTerminal");
				printTokens(parser);
			}

			@Override
			public void visitErrorNode(ErrorNode node) {
				System.out.println("visitErrorNode");
				printTokens(parser);
			}

			@Override
			public void enterRole(RoleContext ctx) {
				System.out.println("enterRole");
				printTokens(parser);
			}

			@Override
			public void enterDirectFlightAction(DirectFlightActionContext ctx) {
				System.out.println("enterDirectFlightAction");
				printTokens(parser);
			}

			@Override
			public void enterCity(CityContext ctx) {
				System.out.println("enterCity");
				printTokens(parser);
			}

			@Override
			public void enterCharterFlightAction(CharterFlightActionContext ctx) {
				printTokens(parser);
			}

			@Override
			public void enterDiscoverAction(DiscoverActionContext ctx) {
				System.out.println("enterDiscoverAction");
				printTokens(parser);
			}

			@Override
			public void enterFerryAction(FerryActionContext ctx) {
				System.out.println("enterFerryAction");
				printTokens(parser);
			}

			@Override
			public void enterQuitAction(QuitActionContext ctx) {
				System.out.println("enterQuitAction");
				printTokens(parser);
			}

			@Override
			public void enterJoinAction(JoinActionContext ctx) {
				System.out.println("enterJoinAction");
				printTokens(parser);
			}

			@Override
			public void enterBuildAction(BuildActionContext ctx) {
				System.out.println("enterBuildAction");
				printTokens(parser);
			}

			@Override
			public void enterAction(ActionContext ctx) {
				System.out.println("enterAction");
				printTokens(parser);
			}

			@Override
			public void enterOperationFlightAction(
					OperationFlightActionContext ctx) {
				System.out.println("enterOperationFlightAction");
				printTokens(parser);
			}

			@Override
			public void enterTreatAction(TreatActionContext ctx) {
				System.out.println("enterTreatAction");
				printTokens(parser);
			}

			@Override
			public void enterPlayer(PlayerContext ctx) {
				System.out.println("enterPlayer");
				printTokens(parser);
			}

		});
		
		parser.action();
	}

	private void printTokens(PandemicParser parser) {
		parser.getExpectedTokensWithinCurrentRule()
			.toList()
			.stream()
			.filter(i -> i >= 0)
			.map(i -> parser.tokenNames[i])
			.peek(System.out::println)
			.collect(GuavaCollectors.toImmutableList());
	}
}
