// Generated from /home/developer/personal/appocalypse/pandemic/grammars/Pandemic.g4 by ANTLR 4.3
package com.github.appocalypse.pandemic.grammars;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PandemicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__76=1, T__75=2, T__74=3, T__73=4, T__72=5, T__71=6, T__70=7, T__69=8, 
		T__68=9, T__67=10, T__66=11, T__65=12, T__64=13, T__63=14, T__62=15, T__61=16, 
		T__60=17, T__59=18, T__58=19, T__57=20, T__56=21, T__55=22, T__54=23, 
		T__53=24, T__52=25, T__51=26, T__50=27, T__49=28, T__48=29, T__47=30, 
		T__46=31, T__45=32, T__44=33, T__43=34, T__42=35, T__41=36, T__40=37, 
		T__39=38, T__38=39, T__37=40, T__36=41, T__35=42, T__34=43, T__33=44, 
		T__32=45, T__31=46, T__30=47, T__29=48, T__28=49, T__27=50, T__26=51, 
		T__25=52, T__24=53, T__23=54, T__22=55, T__21=56, T__20=57, T__19=58, 
		T__18=59, T__17=60, T__16=61, T__15=62, T__14=63, T__13=64, T__12=65, 
		T__11=66, T__10=67, T__9=68, T__8=69, T__7=70, T__6=71, T__5=72, T__4=73, 
		T__3=74, T__2=75, T__1=76, T__0=77, Identifier=78, WS=79;
	public static final String[] tokenNames = {
		"<INVALID>", "'Sydney'", "'Kinshasa'", "'Generalist'", "'Tehran'", "'Chicago'", 
		"'Beijing'", "'Quarantine Specialist'", "'Istanbul'", "'Bogota'", "'discard for'", 
		"'Operations Expert'", "'Buenos Aires'", "'Lima'", "','", "'Algiers'", 
		"'Karachi'", "'join'", "'Jakarta'", "'treat a disease'", "'discard'", 
		"'Essen'", "'discover a cure by discard'", "'Taipei'", "'charter flight from'", 
		"'San Francisco'", "'Johannesburg'", "'Los Angeles'", "'by discard'", 
		"'Montreal'", "'Containment Specialist'", "'Baghdad'", "'direct flight to'", 
		"'Miami'", "'Medic'", "'Shanghai'", "'Delhi'", "'Dispatcher'", "'Bangkok'", 
		"'Sao Paulo'", "';'", "'Lagos'", "'Researcher'", "'Tokyo'", "'Manila'", 
		"'Khartoum'", "'players'", "'for operation flight to'", "'ferry to'", 
		"'Santiago'", "'New York'", "'Washington'", "'Madrid'", "'Contingency Planner'", 
		"'London'", "'the'", "'build a research station'", "'Milan'", "'Paris'", 
		"'Osaka'", "'Moscow'", "'St. Petersburg'", "'Mexico City'", "'Mumbai'", 
		"'Atlanta'", "'Chennai'", "'Kolkata'", "'Cairo'", "'Ho Chi Minh City'", 
		"'Hong Kong'", "'Riyadh'", "'quit'", "'Field Operative'", "'Archivist'", 
		"'Troubleshooter'", "'Epidemiologist'", "'Seoul'", "'Scientist'", "Identifier", 
		"WS"
	};
	public static final int
		RULE_actions = 0, RULE_action = 1, RULE_buildAction = 2, RULE_directFlightAction = 3, 
		RULE_charterFlightAction = 4, RULE_discoverAction = 5, RULE_quitAction = 6, 
		RULE_ferryAction = 7, RULE_operationFlightAction = 8, RULE_joinAction = 9, 
		RULE_treatAction = 10, RULE_player = 11, RULE_city = 12, RULE_role = 13;
	public static final String[] ruleNames = {
		"actions", "action", "buildAction", "directFlightAction", "charterFlightAction", 
		"discoverAction", "quitAction", "ferryAction", "operationFlightAction", 
		"joinAction", "treatAction", "player", "city", "role"
	};

	@Override
	public String getGrammarFileName() { return "Pandemic.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PandemicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ActionsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PandemicParser.EOF, 0); }
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public ActionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterActions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitActions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitActions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionsContext actions() throws RecognitionException {
		ActionsContext _localctx = new ActionsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_actions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (T__67 - 10)) | (1L << (T__58 - 10)) | (1L << (T__57 - 10)) | (1L << (T__55 - 10)) | (1L << (T__53 - 10)) | (1L << (T__45 - 10)) | (1L << (T__31 - 10)) | (1L << (T__29 - 10)) | (1L << (T__21 - 10)) | (1L << (T__6 - 10)))) != 0)) {
				{
				{
				setState(28); action();
				setState(29); match(T__37);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionContext extends ParserRuleContext {
		public TreatActionContext treatAction() {
			return getRuleContext(TreatActionContext.class,0);
		}
		public DirectFlightActionContext directFlightAction() {
			return getRuleContext(DirectFlightActionContext.class,0);
		}
		public OperationFlightActionContext operationFlightAction() {
			return getRuleContext(OperationFlightActionContext.class,0);
		}
		public BuildActionContext buildAction() {
			return getRuleContext(BuildActionContext.class,0);
		}
		public FerryActionContext ferryAction() {
			return getRuleContext(FerryActionContext.class,0);
		}
		public QuitActionContext quitAction() {
			return getRuleContext(QuitActionContext.class,0);
		}
		public JoinActionContext joinAction() {
			return getRuleContext(JoinActionContext.class,0);
		}
		public CharterFlightActionContext charterFlightAction() {
			return getRuleContext(CharterFlightActionContext.class,0);
		}
		public DiscoverActionContext discoverAction() {
			return getRuleContext(DiscoverActionContext.class,0);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_action);
		try {
			setState(47);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38); joinAction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39); buildAction();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40); directFlightAction();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(41); charterFlightAction();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(42); discoverAction();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(43); quitAction();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(44); ferryAction();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(45); operationFlightAction();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(46); treatAction();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuildActionContext extends ParserRuleContext {
		public CityContext city() {
			return getRuleContext(CityContext.class,0);
		}
		public BuildActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_buildAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterBuildAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitBuildAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitBuildAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuildActionContext buildAction() throws RecognitionException {
		BuildActionContext _localctx = new BuildActionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_buildAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); match(T__21);
			setState(52);
			_la = _input.LA(1);
			if (_la==T__49) {
				{
				setState(50); match(T__49);
				setState(51); city();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectFlightActionContext extends ParserRuleContext {
		public CityContext city() {
			return getRuleContext(CityContext.class,0);
		}
		public DirectFlightActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directFlightAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterDirectFlightAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitDirectFlightAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitDirectFlightAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectFlightActionContext directFlightAction() throws RecognitionException {
		DirectFlightActionContext _localctx = new DirectFlightActionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_directFlightAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_la = _input.LA(1);
			if (_la==T__67) {
				{
				setState(54); match(T__67);
				}
			}

			setState(57); match(T__45);
			setState(58); city();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CharterFlightActionContext extends ParserRuleContext {
		public CityContext city() {
			return getRuleContext(CityContext.class,0);
		}
		public CharterFlightActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charterFlightAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterCharterFlightAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitCharterFlightAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitCharterFlightAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharterFlightActionContext charterFlightAction() throws RecognitionException {
		CharterFlightActionContext _localctx = new CharterFlightActionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_charterFlightAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_la = _input.LA(1);
			if (_la==T__67) {
				{
				setState(60); match(T__67);
				}
			}

			setState(63); match(T__53);
			setState(64); city();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DiscoverActionContext extends ParserRuleContext {
		public List<CityContext> city() {
			return getRuleContexts(CityContext.class);
		}
		public CityContext city(int i) {
			return getRuleContext(CityContext.class,i);
		}
		public DiscoverActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_discoverAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterDiscoverAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitDiscoverAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitDiscoverAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DiscoverActionContext discoverAction() throws RecognitionException {
		DiscoverActionContext _localctx = new DiscoverActionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_discoverAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); match(T__55);
			setState(67); city();
			setState(68); match(T__63);
			setState(69); city();
			setState(70); match(T__63);
			setState(71); city();
			setState(72); match(T__63);
			setState(73); city();
			setState(76);
			_la = _input.LA(1);
			if (_la==T__63) {
				{
				setState(74); match(T__63);
				setState(75); city();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuitActionContext extends ParserRuleContext {
		public QuitActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quitAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterQuitAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitQuitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitQuitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuitActionContext quitAction() throws RecognitionException {
		QuitActionContext _localctx = new QuitActionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_quitAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FerryActionContext extends ParserRuleContext {
		public CityContext city() {
			return getRuleContext(CityContext.class,0);
		}
		public FerryActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ferryAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterFerryAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitFerryAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitFerryAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FerryActionContext ferryAction() throws RecognitionException {
		FerryActionContext _localctx = new FerryActionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_ferryAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(T__29);
			setState(81); city();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationFlightActionContext extends ParserRuleContext {
		public List<CityContext> city() {
			return getRuleContexts(CityContext.class);
		}
		public CityContext city(int i) {
			return getRuleContext(CityContext.class,i);
		}
		public OperationFlightActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationFlightAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterOperationFlightAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitOperationFlightAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitOperationFlightAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationFlightActionContext operationFlightAction() throws RecognitionException {
		OperationFlightActionContext _localctx = new OperationFlightActionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_operationFlightAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83); match(T__57);
			setState(84); city();
			setState(85); match(T__30);
			setState(86); city();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JoinActionContext extends ParserRuleContext {
		public List<PlayerContext> player() {
			return getRuleContexts(PlayerContext.class);
		}
		public PlayerContext player(int i) {
			return getRuleContext(PlayerContext.class,i);
		}
		public JoinActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterJoinAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitJoinAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitJoinAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinActionContext joinAction() throws RecognitionException {
		JoinActionContext _localctx = new JoinActionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_joinAction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); match(T__31);
			setState(89); player();
			setState(90); match(T__63);
			setState(91); player();
			setState(102);
			_la = _input.LA(1);
			if (_la==T__63) {
				{
				setState(92); match(T__63);
				setState(93); player();
				setState(100);
				_la = _input.LA(1);
				if (_la==T__63) {
					{
					setState(94); match(T__63);
					setState(95); player();
					setState(98);
					_la = _input.LA(1);
					if (_la==T__63) {
						{
						setState(96); match(T__63);
						setState(97); player();
						}
					}

					}
				}

				}
			}

			setState(104); match(T__60);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TreatActionContext extends ParserRuleContext {
		public TreatActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_treatAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterTreatAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitTreatAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitTreatAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TreatActionContext treatAction() throws RecognitionException {
		TreatActionContext _localctx = new TreatActionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_treatAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); match(T__58);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlayerContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(PandemicParser.Identifier, 0); }
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public PlayerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_player; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterPlayer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitPlayer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitPlayer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlayerContext player() throws RecognitionException {
		PlayerContext _localctx = new PlayerContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_player);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); match(Identifier);
			setState(109); match(T__22);
			setState(110); role();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CityContext extends ParserRuleContext {
		public CityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_city; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterCity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitCity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitCity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CityContext city() throws RecognitionException {
		CityContext _localctx = new CityContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_city);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__76) | (1L << T__75) | (1L << T__73) | (1L << T__72) | (1L << T__71) | (1L << T__69) | (1L << T__68) | (1L << T__65) | (1L << T__64) | (1L << T__62) | (1L << T__61) | (1L << T__59) | (1L << T__56) | (1L << T__54) | (1L << T__52) | (1L << T__51) | (1L << T__50) | (1L << T__48) | (1L << T__46) | (1L << T__44) | (1L << T__42) | (1L << T__41) | (1L << T__39) | (1L << T__38) | (1L << T__36) | (1L << T__34) | (1L << T__33) | (1L << T__32) | (1L << T__28) | (1L << T__27) | (1L << T__26) | (1L << T__25) | (1L << T__23) | (1L << T__20) | (1L << T__19) | (1L << T__18) | (1L << T__17) | (1L << T__16) | (1L << T__15) | (1L << T__14))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (T__13 - 64)) | (1L << (T__12 - 64)) | (1L << (T__11 - 64)) | (1L << (T__10 - 64)) | (1L << (T__9 - 64)) | (1L << (T__8 - 64)) | (1L << (T__7 - 64)) | (1L << (T__1 - 64)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RoleContext extends ParserRuleContext {
		public RoleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_role; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).enterRole(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PandemicListener ) ((PandemicListener)listener).exitRole(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PandemicVisitor ) return ((PandemicVisitor<? extends T>)visitor).visitRole(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleContext role() throws RecognitionException {
		RoleContext _localctx = new RoleContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_role);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__74) | (1L << T__70) | (1L << T__66) | (1L << T__47) | (1L << T__43) | (1L << T__40) | (1L << T__35) | (1L << T__24))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (T__5 - 72)) | (1L << (T__4 - 72)) | (1L << (T__3 - 72)) | (1L << (T__2 - 72)) | (1L << (T__0 - 72)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3Qw\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\7\2\"\n\2\f\2\16\2%\13\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\62\n\3\3\4\3\4\3\4\5"+
		"\4\67\n\4\3\5\5\5:\n\5\3\5\3\5\3\5\3\6\5\6@\n\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7O\n\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13e\n\13"+
		"\5\13g\n\13\5\13i\n\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\4\26\2\3\4\6\b"+
		"\n\13\16\17\21\22\24\24\27\27\31\31\33\35\37\37!!##%&()++-/\63\6688;H"+
		"NN\f\2\5\5\t\t\r\r  $$\'\',,\67\67JMOOx\2#\3\2\2\2\4\61\3\2\2\2\6\63\3"+
		"\2\2\2\b9\3\2\2\2\n?\3\2\2\2\fD\3\2\2\2\16P\3\2\2\2\20R\3\2\2\2\22U\3"+
		"\2\2\2\24Z\3\2\2\2\26l\3\2\2\2\30n\3\2\2\2\32r\3\2\2\2\34t\3\2\2\2\36"+
		"\37\5\4\3\2\37 \7*\2\2 \"\3\2\2\2!\36\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3"+
		"\2\2\2$&\3\2\2\2%#\3\2\2\2&\'\7\2\2\3\'\3\3\2\2\2(\62\5\24\13\2)\62\5"+
		"\6\4\2*\62\5\b\5\2+\62\5\n\6\2,\62\5\f\7\2-\62\5\16\b\2.\62\5\20\t\2/"+
		"\62\5\22\n\2\60\62\5\26\f\2\61(\3\2\2\2\61)\3\2\2\2\61*\3\2\2\2\61+\3"+
		"\2\2\2\61,\3\2\2\2\61-\3\2\2\2\61.\3\2\2\2\61/\3\2\2\2\61\60\3\2\2\2\62"+
		"\5\3\2\2\2\63\66\7:\2\2\64\65\7\36\2\2\65\67\5\32\16\2\66\64\3\2\2\2\66"+
		"\67\3\2\2\2\67\7\3\2\2\28:\7\f\2\298\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\7\""+
		"\2\2<=\5\32\16\2=\t\3\2\2\2>@\7\f\2\2?>\3\2\2\2?@\3\2\2\2@A\3\2\2\2AB"+
		"\7\32\2\2BC\5\32\16\2C\13\3\2\2\2DE\7\30\2\2EF\5\32\16\2FG\7\20\2\2GH"+
		"\5\32\16\2HI\7\20\2\2IJ\5\32\16\2JK\7\20\2\2KN\5\32\16\2LM\7\20\2\2MO"+
		"\5\32\16\2NL\3\2\2\2NO\3\2\2\2O\r\3\2\2\2PQ\7I\2\2Q\17\3\2\2\2RS\7\62"+
		"\2\2ST\5\32\16\2T\21\3\2\2\2UV\7\26\2\2VW\5\32\16\2WX\7\61\2\2XY\5\32"+
		"\16\2Y\23\3\2\2\2Z[\7\60\2\2[\\\5\30\r\2\\]\7\20\2\2]h\5\30\r\2^_\7\20"+
		"\2\2_f\5\30\r\2`a\7\20\2\2ad\5\30\r\2bc\7\20\2\2ce\5\30\r\2db\3\2\2\2"+
		"de\3\2\2\2eg\3\2\2\2f`\3\2\2\2fg\3\2\2\2gi\3\2\2\2h^\3\2\2\2hi\3\2\2\2"+
		"ij\3\2\2\2jk\7\23\2\2k\25\3\2\2\2lm\7\25\2\2m\27\3\2\2\2no\7P\2\2op\7"+
		"9\2\2pq\5\34\17\2q\31\3\2\2\2rs\t\2\2\2s\33\3\2\2\2tu\t\3\2\2u\35\3\2"+
		"\2\2\13#\61\669?Ndfh";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}