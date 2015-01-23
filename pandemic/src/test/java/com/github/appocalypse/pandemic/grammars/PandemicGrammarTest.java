package com.github.appocalypse.pandemic.grammars;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;

import com.github.appocalypse.pandemic.grammars.PandemicParser.ActionContext;
import com.github.appocalypse.pandemic.grammars.PandemicParser.ActionsContext;
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
		ANTLRInputStream antlrInputStream = new ANTLRInputStream("discover a cure by discard Ho Chi Minh City"
				+ " , Atlanta , Washington , Chicag");
		
		PandemicLexer lexer = new PandemicLexer(antlrInputStream);
		CommonTokenStream commonsTokenStream = new CommonTokenStream(lexer);
		PandemicParser parser = new PandemicParser(commonsTokenStream);
		parser.addParseListener(new PandemicListener() {

			@Override
			public void visitTerminal(TerminalNode node) {
				System.out.println("visitTerminal");
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void visitErrorNode(ErrorNode node) {
				System.out.println("visitErrorNode");
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterEveryRule(ParserRuleContext ctx) {
				System.out.println("enterEveryRule");
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitEveryRule(ParserRuleContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterRole(RoleContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitRole(RoleContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterDirectFlightAction(DirectFlightActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitDirectFlightAction(DirectFlightActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterCity(CityContext ctx) {
				System.out.println("enterCity");
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitCity(CityContext ctx) {
				System.out.println("exitCity");
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterCharterFlightAction(CharterFlightActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitCharterFlightAction(CharterFlightActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterDiscoverAction(DiscoverActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitDiscoverAction(DiscoverActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterFerryAction(FerryActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitFerryAction(FerryActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterQuitAction(QuitActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitQuitAction(QuitActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterJoinAction(JoinActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitJoinAction(JoinActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterBuildAction(BuildActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitBuildAction(BuildActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterAction(ActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitAction(ActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterActions(ActionsContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitActions(ActionsContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterOperationFlightAction(
					OperationFlightActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitOperationFlightAction(
					OperationFlightActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterTreatAction(TreatActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitTreatAction(TreatActionContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void enterPlayer(PlayerContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}

			@Override
			public void exitPlayer(PlayerContext ctx) {
				System.out.println(parser.getExpectedTokensWithinCurrentRule().toList());				
			}
		});
		
		parser.actions();
	}

}
