// Generated from /home/developer/personal/appocalypse/pandemic/grammars/Pandemic.g4 by ANTLR 4.3
package com.github.appocalypse.pandemic.grammars;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PandemicParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PandemicVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PandemicParser#role}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRole(@NotNull PandemicParser.RoleContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#directFlightAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectFlightAction(@NotNull PandemicParser.DirectFlightActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#city}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCity(@NotNull PandemicParser.CityContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#charterFlightAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharterFlightAction(@NotNull PandemicParser.CharterFlightActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#discoverAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiscoverAction(@NotNull PandemicParser.DiscoverActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#ferryAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFerryAction(@NotNull PandemicParser.FerryActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#quitAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuitAction(@NotNull PandemicParser.QuitActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#joinAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinAction(@NotNull PandemicParser.JoinActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#buildAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuildAction(@NotNull PandemicParser.BuildActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(@NotNull PandemicParser.ActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#actions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActions(@NotNull PandemicParser.ActionsContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#operationFlightAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperationFlightAction(@NotNull PandemicParser.OperationFlightActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#treatAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTreatAction(@NotNull PandemicParser.TreatActionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PandemicParser#player}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlayer(@NotNull PandemicParser.PlayerContext ctx);
}