// Generated from /home/developer/personal/appocalypse/pandemic/grammars/Pandemic.g4 by ANTLR 4.3
package com.github.appocalypse.pandemic.grammars;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PandemicParser}.
 */
public interface PandemicListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PandemicParser#role}.
	 * @param ctx the parse tree
	 */
	void enterRole(@NotNull PandemicParser.RoleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#role}.
	 * @param ctx the parse tree
	 */
	void exitRole(@NotNull PandemicParser.RoleContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#directFlightAction}.
	 * @param ctx the parse tree
	 */
	void enterDirectFlightAction(@NotNull PandemicParser.DirectFlightActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#directFlightAction}.
	 * @param ctx the parse tree
	 */
	void exitDirectFlightAction(@NotNull PandemicParser.DirectFlightActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#city}.
	 * @param ctx the parse tree
	 */
	void enterCity(@NotNull PandemicParser.CityContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#city}.
	 * @param ctx the parse tree
	 */
	void exitCity(@NotNull PandemicParser.CityContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#charterFlightAction}.
	 * @param ctx the parse tree
	 */
	void enterCharterFlightAction(@NotNull PandemicParser.CharterFlightActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#charterFlightAction}.
	 * @param ctx the parse tree
	 */
	void exitCharterFlightAction(@NotNull PandemicParser.CharterFlightActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#discoverAction}.
	 * @param ctx the parse tree
	 */
	void enterDiscoverAction(@NotNull PandemicParser.DiscoverActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#discoverAction}.
	 * @param ctx the parse tree
	 */
	void exitDiscoverAction(@NotNull PandemicParser.DiscoverActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#ferryAction}.
	 * @param ctx the parse tree
	 */
	void enterFerryAction(@NotNull PandemicParser.FerryActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#ferryAction}.
	 * @param ctx the parse tree
	 */
	void exitFerryAction(@NotNull PandemicParser.FerryActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#quitAction}.
	 * @param ctx the parse tree
	 */
	void enterQuitAction(@NotNull PandemicParser.QuitActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#quitAction}.
	 * @param ctx the parse tree
	 */
	void exitQuitAction(@NotNull PandemicParser.QuitActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#joinAction}.
	 * @param ctx the parse tree
	 */
	void enterJoinAction(@NotNull PandemicParser.JoinActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#joinAction}.
	 * @param ctx the parse tree
	 */
	void exitJoinAction(@NotNull PandemicParser.JoinActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#buildAction}.
	 * @param ctx the parse tree
	 */
	void enterBuildAction(@NotNull PandemicParser.BuildActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#buildAction}.
	 * @param ctx the parse tree
	 */
	void exitBuildAction(@NotNull PandemicParser.BuildActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#action}.
	 * @param ctx the parse tree
	 */
	void enterAction(@NotNull PandemicParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#action}.
	 * @param ctx the parse tree
	 */
	void exitAction(@NotNull PandemicParser.ActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#actions}.
	 * @param ctx the parse tree
	 */
	void enterActions(@NotNull PandemicParser.ActionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#actions}.
	 * @param ctx the parse tree
	 */
	void exitActions(@NotNull PandemicParser.ActionsContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#operationFlightAction}.
	 * @param ctx the parse tree
	 */
	void enterOperationFlightAction(@NotNull PandemicParser.OperationFlightActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#operationFlightAction}.
	 * @param ctx the parse tree
	 */
	void exitOperationFlightAction(@NotNull PandemicParser.OperationFlightActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#treatAction}.
	 * @param ctx the parse tree
	 */
	void enterTreatAction(@NotNull PandemicParser.TreatActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#treatAction}.
	 * @param ctx the parse tree
	 */
	void exitTreatAction(@NotNull PandemicParser.TreatActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link PandemicParser#player}.
	 * @param ctx the parse tree
	 */
	void enterPlayer(@NotNull PandemicParser.PlayerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PandemicParser#player}.
	 * @param ctx the parse tree
	 */
	void exitPlayer(@NotNull PandemicParser.PlayerContext ctx);
}