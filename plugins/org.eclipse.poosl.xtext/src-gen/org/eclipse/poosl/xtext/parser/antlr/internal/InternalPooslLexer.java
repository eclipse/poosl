package org.eclipse.poosl.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPooslLexer extends Lexer {
    public static final int T__50=50;
    public static final int RULE_CHARACTER=5;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=19;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int RULE_MLC_ANY=15;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_HEXADECIMAL_CORE=9;
    public static final int RULE_REAL_CORE=10;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int RULE_DECIMAL_CORE=7;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int RULE_FLOAT_CORE=11;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__90=90;
    public static final int T__99=99;
    public static final int RULE_CHARACTER_ELEMENT=21;
    public static final int RULE_POOSL_STRING_ELEMENT=22;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int RULE_ESCAPE_ZERO=24;
    public static final int RULE_MLC_BODY=16;
    public static final int RULE_ESCAPE_SEQUENCE=23;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int RULE_ENVIRONMENT_VARIABLE_NAME=6;
    public static final int T__25=25;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_MLC_STAR=18;
    public static final int RULE_DIGITS=20;
    public static final int RULE_SL_COMMENT=14;
    public static final int RULE_BINARY_CORE=8;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=13;
    public static final int RULE_POOSL_STRING=4;
    public static final int RULE_MLC_SLASH=17;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int RULE_IDENTIFIER_CORE=12;
    public static final int T__87=87;

    // delegates
    // delegators

    public InternalPooslLexer() {;} 
    public InternalPooslLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalPooslLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalPoosl.g"; }

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:11:7: ( 'import' )
            // InternalPoosl.g:11:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:12:7: ( 'importlib' )
            // InternalPoosl.g:12:9: 'importlib'
            {
            match("importlib"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:13:7: ( 'native' )
            // InternalPoosl.g:13:9: 'native'
            {
            match("native"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:14:7: ( 'data' )
            // InternalPoosl.g:14:9: 'data'
            {
            match("data"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:15:7: ( 'class' )
            // InternalPoosl.g:15:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:16:7: ( 'extends' )
            // InternalPoosl.g:16:9: 'extends'
            {
            match("extends"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:17:7: ( 'variables' )
            // InternalPoosl.g:17:9: 'variables'
            {
            match("variables"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:18:7: ( ',' )
            // InternalPoosl.g:18:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:19:7: ( 'methods' )
            // InternalPoosl.g:19:9: 'methods'
            {
            match("methods"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:20:7: ( '(' )
            // InternalPoosl.g:20:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:21:7: ( ')' )
            // InternalPoosl.g:21:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:22:7: ( ':' )
            // InternalPoosl.g:22:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:23:7: ( '|' )
            // InternalPoosl.g:23:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:24:7: ( '@' )
            // InternalPoosl.g:24:9: '@'
            {
            match('@'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:25:7: ( 'process' )
            // InternalPoosl.g:25:9: 'process'
            {
            match("process"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:26:7: ( 'ports' )
            // InternalPoosl.g:26:9: 'ports'
            {
            match("ports"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:27:7: ( 'messages' )
            // InternalPoosl.g:27:9: 'messages'
            {
            match("messages"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:28:7: ( 'init' )
            // InternalPoosl.g:28:9: 'init'
            {
            match("init"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:29:7: ( '?' )
            // InternalPoosl.g:29:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:30:7: ( '!' )
            // InternalPoosl.g:30:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:31:7: ( 'system' )
            // InternalPoosl.g:31:9: 'system'
            {
            match("system"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:32:7: ( 'instances' )
            // InternalPoosl.g:32:9: 'instances'
            {
            match("instances"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:33:7: ( 'channels' )
            // InternalPoosl.g:33:9: 'channels'
            {
            match("channels"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:34:7: ( 'cluster' )
            // InternalPoosl.g:34:9: 'cluster'
            {
            match("cluster"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:35:7: ( ':=' )
            // InternalPoosl.g:35:9: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:36:7: ( '{' )
            // InternalPoosl.g:36:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:37:7: ( '}' )
            // InternalPoosl.g:37:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:38:7: ( '.' )
            // InternalPoosl.g:38:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:39:7: ( ';' )
            // InternalPoosl.g:39:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:40:7: ( 'return' )
            // InternalPoosl.g:40:9: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:41:7: ( '^' )
            // InternalPoosl.g:41:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:42:7: ( 'if' )
            // InternalPoosl.g:42:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:43:7: ( 'then' )
            // InternalPoosl.g:43:9: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:44:7: ( 'else' )
            // InternalPoosl.g:44:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:45:7: ( 'fi' )
            // InternalPoosl.g:45:9: 'fi'
            {
            match("fi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:46:7: ( 'while' )
            // InternalPoosl.g:46:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:47:7: ( 'do' )
            // InternalPoosl.g:47:9: 'do'
            {
            match("do"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:48:7: ( 'od' )
            // InternalPoosl.g:48:9: 'od'
            {
            match("od"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:49:7: ( 'switch' )
            // InternalPoosl.g:49:9: 'switch'
            {
            match("switch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:50:7: ( 'default' )
            // InternalPoosl.g:50:9: 'default'
            {
            match("default"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:51:7: ( 'case' )
            // InternalPoosl.g:51:9: 'case'
            {
            match("case"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:52:7: ( 'currentTime' )
            // InternalPoosl.g:52:9: 'currentTime'
            {
            match("currentTime"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:53:7: ( 'self' )
            // InternalPoosl.g:53:9: 'self'
            {
            match("self"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:54:7: ( 'true' )
            // InternalPoosl.g:54:9: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:55:7: ( 'false' )
            // InternalPoosl.g:55:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:56:7: ( 'nil' )
            // InternalPoosl.g:56:9: 'nil'
            {
            match("nil"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:57:7: ( 'new' )
            // InternalPoosl.g:57:9: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:58:7: ( 'delay' )
            // InternalPoosl.g:58:9: 'delay'
            {
            match("delay"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:59:7: ( 'skip' )
            // InternalPoosl.g:59:9: 'skip'
            {
            match("skip"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:60:7: ( '[' )
            // InternalPoosl.g:60:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:61:7: ( ']' )
            // InternalPoosl.g:61:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:62:7: ( 'par' )
            // InternalPoosl.g:62:9: 'par'
            {
            match("par"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:63:7: ( 'and' )
            // InternalPoosl.g:63:9: 'and'
            {
            match("and"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:64:7: ( 'rap' )
            // InternalPoosl.g:64:9: 'rap'
            {
            match("rap"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:65:7: ( 'sel' )
            // InternalPoosl.g:65:9: 'sel'
            {
            match("sel"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:66:7: ( 'or' )
            // InternalPoosl.g:66:9: 'or'
            {
            match("or"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:67:7: ( 'les' )
            // InternalPoosl.g:67:9: 'les'
            {
            match("les"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:68:7: ( 'abort' )
            // InternalPoosl.g:68:9: 'abort'
            {
            match("abort"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:69:7: ( 'with' )
            // InternalPoosl.g:69:9: 'with'
            {
            match("with"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:70:7: ( 'interrupt' )
            // InternalPoosl.g:70:9: 'interrupt'
            {
            match("interrupt"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:71:7: ( '-' )
            // InternalPoosl.g:71:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:72:7: ( '+' )
            // InternalPoosl.g:72:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:73:7: ( 'nan' )
            // InternalPoosl.g:73:9: 'nan'
            {
            match("nan"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:74:7: ( 'inf' )
            // InternalPoosl.g:74:9: 'inf'
            {
            match("inf"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:75:7: ( '=' )
            // InternalPoosl.g:75:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:76:7: ( '!=' )
            // InternalPoosl.g:76:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:77:7: ( '==' )
            // InternalPoosl.g:77:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:78:7: ( '!==' )
            // InternalPoosl.g:78:9: '!=='
            {
            match("!=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:79:7: ( '<' )
            // InternalPoosl.g:79:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:80:7: ( '<=' )
            // InternalPoosl.g:80:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:81:7: ( '>' )
            // InternalPoosl.g:81:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:82:7: ( '>=' )
            // InternalPoosl.g:82:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__97"
    public final void mT__97() throws RecognitionException {
        try {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:83:7: ( '&' )
            // InternalPoosl.g:83:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__98"
    public final void mT__98() throws RecognitionException {
        try {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:84:7: ( '*' )
            // InternalPoosl.g:84:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
        try {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:85:7: ( '/' )
            // InternalPoosl.g:85:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__99"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8834:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalPoosl.g:8834:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalPoosl.g:8834:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalPoosl.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8836:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalPoosl.g:8836:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalPoosl.g:8836:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='\f')||(LA2_0>='\u000E' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalPoosl.g:8836:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalPoosl.g:8836:40: ( ( '\\r' )? '\\n' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\n'||LA4_0=='\r') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalPoosl.g:8836:41: ( '\\r' )? '\\n'
                    {
                    // InternalPoosl.g:8836:41: ( '\\r' )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='\r') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // InternalPoosl.g:8836:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_MLC_ANY"
    public final void mRULE_MLC_ANY() throws RecognitionException {
        try {
            // InternalPoosl.g:8838:23: (~ ( ( '*' | '/' ) ) )
            // InternalPoosl.g:8838:25: ~ ( ( '*' | '/' ) )
            {
            if ( (input.LA(1)>='\u0000' && input.LA(1)<=')')||(input.LA(1)>='+' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_MLC_ANY"

    // $ANTLR start "RULE_MLC_SLASH"
    public final void mRULE_MLC_SLASH() throws RecognitionException {
        try {
            // InternalPoosl.g:8840:25: ( ( '/' )+ ( '*' RULE_MLC_BODY | RULE_MLC_ANY ) )
            // InternalPoosl.g:8840:27: ( '/' )+ ( '*' RULE_MLC_BODY | RULE_MLC_ANY )
            {
            // InternalPoosl.g:8840:27: ( '/' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0=='/') ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalPoosl.g:8840:27: '/'
            	    {
            	    match('/'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

            // InternalPoosl.g:8840:32: ( '*' RULE_MLC_BODY | RULE_MLC_ANY )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='*') ) {
                alt6=1;
            }
            else if ( ((LA6_0>='\u0000' && LA6_0<=')')||(LA6_0>='+' && LA6_0<='.')||(LA6_0>='0' && LA6_0<='\uFFFF')) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalPoosl.g:8840:33: '*' RULE_MLC_BODY
                    {
                    match('*'); 
                    mRULE_MLC_BODY(); 

                    }
                    break;
                case 2 :
                    // InternalPoosl.g:8840:51: RULE_MLC_ANY
                    {
                    mRULE_MLC_ANY(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_MLC_SLASH"

    // $ANTLR start "RULE_MLC_STAR"
    public final void mRULE_MLC_STAR() throws RecognitionException {
        try {
            // InternalPoosl.g:8842:24: ( ( '*' )+ RULE_MLC_ANY )
            // InternalPoosl.g:8842:26: ( '*' )+ RULE_MLC_ANY
            {
            // InternalPoosl.g:8842:26: ( '*' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalPoosl.g:8842:26: '*'
            	    {
            	    match('*'); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            mRULE_MLC_ANY(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_MLC_STAR"

    // $ANTLR start "RULE_MLC_BODY"
    public final void mRULE_MLC_BODY() throws RecognitionException {
        try {
            // InternalPoosl.g:8844:24: ( ( RULE_MLC_ANY | RULE_MLC_SLASH | RULE_MLC_STAR )* ( '*' )+ '/' )
            // InternalPoosl.g:8844:26: ( RULE_MLC_ANY | RULE_MLC_SLASH | RULE_MLC_STAR )* ( '*' )+ '/'
            {
            // InternalPoosl.g:8844:26: ( RULE_MLC_ANY | RULE_MLC_SLASH | RULE_MLC_STAR )*
            loop8:
            do {
                int alt8=4;
                alt8 = dfa8.predict(input);
                switch (alt8) {
            	case 1 :
            	    // InternalPoosl.g:8844:27: RULE_MLC_ANY
            	    {
            	    mRULE_MLC_ANY(); 

            	    }
            	    break;
            	case 2 :
            	    // InternalPoosl.g:8844:40: RULE_MLC_SLASH
            	    {
            	    mRULE_MLC_SLASH(); 

            	    }
            	    break;
            	case 3 :
            	    // InternalPoosl.g:8844:55: RULE_MLC_STAR
            	    {
            	    mRULE_MLC_STAR(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalPoosl.g:8844:71: ( '*' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalPoosl.g:8844:71: '*'
            	    {
            	    match('*'); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            match('/'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_MLC_BODY"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8846:17: ( '/*' RULE_MLC_BODY )
            // InternalPoosl.g:8846:19: '/*' RULE_MLC_BODY
            {
            match("/*"); 

            mRULE_MLC_BODY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_BINARY_CORE"
    public final void mRULE_BINARY_CORE() throws RecognitionException {
        try {
            int _type = RULE_BINARY_CORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8848:18: ( '0' ( 'b' | 'B' ) ( '0' .. '1' )+ )
            // InternalPoosl.g:8848:20: '0' ( 'b' | 'B' ) ( '0' .. '1' )+
            {
            match('0'); 
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalPoosl.g:8848:34: ( '0' .. '1' )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='0' && LA10_0<='1')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalPoosl.g:8848:35: '0' .. '1'
            	    {
            	    matchRange('0','1'); 

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BINARY_CORE"

    // $ANTLR start "RULE_HEXADECIMAL_CORE"
    public final void mRULE_HEXADECIMAL_CORE() throws RecognitionException {
        try {
            int _type = RULE_HEXADECIMAL_CORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8850:23: ( '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ )
            // InternalPoosl.g:8850:25: '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
            {
            match('0'); 
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalPoosl.g:8850:39: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='0' && LA11_0<='9')||(LA11_0>='A' && LA11_0<='F')||(LA11_0>='a' && LA11_0<='f')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalPoosl.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_HEXADECIMAL_CORE"

    // $ANTLR start "RULE_DIGITS"
    public final void mRULE_DIGITS() throws RecognitionException {
        try {
            // InternalPoosl.g:8852:22: ( ( '0' .. '9' | '1' .. '9' ( '0' .. '9' )+ ) )
            // InternalPoosl.g:8852:24: ( '0' .. '9' | '1' .. '9' ( '0' .. '9' )+ )
            {
            // InternalPoosl.g:8852:24: ( '0' .. '9' | '1' .. '9' ( '0' .. '9' )+ )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>='1' && LA13_0<='9')) ) {
                int LA13_1 = input.LA(2);

                if ( ((LA13_1>='0' && LA13_1<='9')) ) {
                    alt13=2;
                }
                else {
                    alt13=1;}
            }
            else if ( (LA13_0=='0') ) {
                alt13=1;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalPoosl.g:8852:25: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }
                    break;
                case 2 :
                    // InternalPoosl.g:8852:34: '1' .. '9' ( '0' .. '9' )+
                    {
                    matchRange('1','9'); 
                    // InternalPoosl.g:8852:43: ( '0' .. '9' )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // InternalPoosl.g:8852:44: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt12 >= 1 ) break loop12;
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
                    } while (true);


                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_DIGITS"

    // $ANTLR start "RULE_DECIMAL_CORE"
    public final void mRULE_DECIMAL_CORE() throws RecognitionException {
        try {
            int _type = RULE_DECIMAL_CORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8854:19: ( RULE_DIGITS ( ( 'e' | 'E' ) ( '+' )? ( '0' .. '9' )+ )? )
            // InternalPoosl.g:8854:21: RULE_DIGITS ( ( 'e' | 'E' ) ( '+' )? ( '0' .. '9' )+ )?
            {
            mRULE_DIGITS(); 
            // InternalPoosl.g:8854:33: ( ( 'e' | 'E' ) ( '+' )? ( '0' .. '9' )+ )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='E'||LA16_0=='e') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalPoosl.g:8854:34: ( 'e' | 'E' ) ( '+' )? ( '0' .. '9' )+
                    {
                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // InternalPoosl.g:8854:44: ( '+' )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='+') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalPoosl.g:8854:44: '+'
                            {
                            match('+'); 

                            }
                            break;

                    }

                    // InternalPoosl.g:8854:49: ( '0' .. '9' )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // InternalPoosl.g:8854:50: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DECIMAL_CORE"

    // $ANTLR start "RULE_REAL_CORE"
    public final void mRULE_REAL_CORE() throws RecognitionException {
        try {
            int _type = RULE_REAL_CORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8856:16: ( ( RULE_DIGITS '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ ) ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )? )
            // InternalPoosl.g:8856:18: ( RULE_DIGITS '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ ) ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
            {
            // InternalPoosl.g:8856:18: ( RULE_DIGITS '.' ( '0' .. '9' )* | '.' ( '0' .. '9' )+ )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>='0' && LA19_0<='9')) ) {
                alt19=1;
            }
            else if ( (LA19_0=='.') ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalPoosl.g:8856:19: RULE_DIGITS '.' ( '0' .. '9' )*
                    {
                    mRULE_DIGITS(); 
                    match('.'); 
                    // InternalPoosl.g:8856:35: ( '0' .. '9' )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalPoosl.g:8856:36: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalPoosl.g:8856:47: '.' ( '0' .. '9' )+
                    {
                    match('.'); 
                    // InternalPoosl.g:8856:51: ( '0' .. '9' )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>='0' && LA18_0<='9')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalPoosl.g:8856:52: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt18 >= 1 ) break loop18;
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
                    } while (true);


                    }
                    break;

            }

            // InternalPoosl.g:8856:64: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='E'||LA22_0=='e') ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalPoosl.g:8856:65: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
                    {
                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // InternalPoosl.g:8856:75: ( '+' | '-' )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0=='+'||LA20_0=='-') ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // InternalPoosl.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }

                    // InternalPoosl.g:8856:86: ( '0' .. '9' )+
                    int cnt21=0;
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( ((LA21_0>='0' && LA21_0<='9')) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // InternalPoosl.g:8856:87: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt21 >= 1 ) break loop21;
                                EarlyExitException eee =
                                    new EarlyExitException(21, input);
                                throw eee;
                        }
                        cnt21++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_REAL_CORE"

    // $ANTLR start "RULE_FLOAT_CORE"
    public final void mRULE_FLOAT_CORE() throws RecognitionException {
        try {
            int _type = RULE_FLOAT_CORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8858:17: ( RULE_REAL_CORE ( 'f' | 'F' ) )
            // InternalPoosl.g:8858:19: RULE_REAL_CORE ( 'f' | 'F' )
            {
            mRULE_REAL_CORE(); 
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_FLOAT_CORE"

    // $ANTLR start "RULE_IDENTIFIER_CORE"
    public final void mRULE_IDENTIFIER_CORE() throws RecognitionException {
        try {
            int _type = RULE_IDENTIFIER_CORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8860:22: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // InternalPoosl.g:8860:24: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalPoosl.g:8860:44: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>='0' && LA23_0<='9')||(LA23_0>='A' && LA23_0<='Z')||LA23_0=='_'||(LA23_0>='a' && LA23_0<='z')) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalPoosl.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_IDENTIFIER_CORE"

    // $ANTLR start "RULE_CHARACTER"
    public final void mRULE_CHARACTER() throws RecognitionException {
        try {
            int _type = RULE_CHARACTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8862:16: ( '\\'' RULE_CHARACTER_ELEMENT '\\'' )
            // InternalPoosl.g:8862:18: '\\'' RULE_CHARACTER_ELEMENT '\\''
            {
            match('\''); 
            mRULE_CHARACTER_ELEMENT(); 
            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_CHARACTER"

    // $ANTLR start "RULE_POOSL_STRING"
    public final void mRULE_POOSL_STRING() throws RecognitionException {
        try {
            int _type = RULE_POOSL_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8864:19: ( '\"' ( RULE_POOSL_STRING_ELEMENT )* '\"' )
            // InternalPoosl.g:8864:21: '\"' ( RULE_POOSL_STRING_ELEMENT )* '\"'
            {
            match('\"'); 
            // InternalPoosl.g:8864:25: ( RULE_POOSL_STRING_ELEMENT )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0=='\t'||(LA24_0>=' ' && LA24_0<='!')||(LA24_0>='#' && LA24_0<='\u00FF')) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalPoosl.g:8864:25: RULE_POOSL_STRING_ELEMENT
            	    {
            	    mRULE_POOSL_STRING_ELEMENT(); 

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_POOSL_STRING"

    // $ANTLR start "RULE_ENVIRONMENT_VARIABLE_NAME"
    public final void mRULE_ENVIRONMENT_VARIABLE_NAME() throws RecognitionException {
        try {
            int _type = RULE_ENVIRONMENT_VARIABLE_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalPoosl.g:8866:32: ( '${' ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )+ '}' )
            // InternalPoosl.g:8866:34: '${' ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )+ '}'
            {
            match("${"); 

            // InternalPoosl.g:8866:39: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>='0' && LA25_0<='9')||(LA25_0>='A' && LA25_0<='Z')||LA25_0=='_'||(LA25_0>='a' && LA25_0<='z')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalPoosl.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);

            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ENVIRONMENT_VARIABLE_NAME"

    // $ANTLR start "RULE_CHARACTER_ELEMENT"
    public final void mRULE_CHARACTER_ELEMENT() throws RecognitionException {
        try {
            // InternalPoosl.g:8868:33: ( ( '\\t' | ' ' .. '&' | '(' .. '[' | ']' .. '\\u00FF' | RULE_ESCAPE_SEQUENCE | RULE_ESCAPE_ZERO ) )
            // InternalPoosl.g:8868:35: ( '\\t' | ' ' .. '&' | '(' .. '[' | ']' .. '\\u00FF' | RULE_ESCAPE_SEQUENCE | RULE_ESCAPE_ZERO )
            {
            // InternalPoosl.g:8868:35: ( '\\t' | ' ' .. '&' | '(' .. '[' | ']' .. '\\u00FF' | RULE_ESCAPE_SEQUENCE | RULE_ESCAPE_ZERO )
            int alt26=6;
            alt26 = dfa26.predict(input);
            switch (alt26) {
                case 1 :
                    // InternalPoosl.g:8868:36: '\\t'
                    {
                    match('\t'); 

                    }
                    break;
                case 2 :
                    // InternalPoosl.g:8868:41: ' ' .. '&'
                    {
                    matchRange(' ','&'); 

                    }
                    break;
                case 3 :
                    // InternalPoosl.g:8868:50: '(' .. '['
                    {
                    matchRange('(','['); 

                    }
                    break;
                case 4 :
                    // InternalPoosl.g:8868:59: ']' .. '\\u00FF'
                    {
                    matchRange(']','\u00FF'); 

                    }
                    break;
                case 5 :
                    // InternalPoosl.g:8868:73: RULE_ESCAPE_SEQUENCE
                    {
                    mRULE_ESCAPE_SEQUENCE(); 

                    }
                    break;
                case 6 :
                    // InternalPoosl.g:8868:94: RULE_ESCAPE_ZERO
                    {
                    mRULE_ESCAPE_ZERO(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_CHARACTER_ELEMENT"

    // $ANTLR start "RULE_POOSL_STRING_ELEMENT"
    public final void mRULE_POOSL_STRING_ELEMENT() throws RecognitionException {
        try {
            // InternalPoosl.g:8870:36: ( ( '\\t' | ' ' .. '!' | '#' .. '[' | ']' .. '\\u00FF' | RULE_ESCAPE_SEQUENCE ) )
            // InternalPoosl.g:8870:38: ( '\\t' | ' ' .. '!' | '#' .. '[' | ']' .. '\\u00FF' | RULE_ESCAPE_SEQUENCE )
            {
            // InternalPoosl.g:8870:38: ( '\\t' | ' ' .. '!' | '#' .. '[' | ']' .. '\\u00FF' | RULE_ESCAPE_SEQUENCE )
            int alt27=5;
            switch ( input.LA(1) ) {
            case '\t':
                {
                alt27=1;
                }
                break;
            case ' ':
            case '!':
                {
                alt27=2;
                }
                break;
            case '#':
            case '$':
            case '%':
            case '&':
            case '\'':
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
            case '-':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
                {
                alt27=3;
                }
                break;
            case ']':
            case '^':
            case '_':
            case '`':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
            case '{':
            case '|':
            case '}':
            case '~':
            case '\u007F':
            case '\u0080':
            case '\u0081':
            case '\u0082':
            case '\u0083':
            case '\u0084':
            case '\u0085':
            case '\u0086':
            case '\u0087':
            case '\u0088':
            case '\u0089':
            case '\u008A':
            case '\u008B':
            case '\u008C':
            case '\u008D':
            case '\u008E':
            case '\u008F':
            case '\u0090':
            case '\u0091':
            case '\u0092':
            case '\u0093':
            case '\u0094':
            case '\u0095':
            case '\u0096':
            case '\u0097':
            case '\u0098':
            case '\u0099':
            case '\u009A':
            case '\u009B':
            case '\u009C':
            case '\u009D':
            case '\u009E':
            case '\u009F':
            case '\u00A0':
            case '\u00A1':
            case '\u00A2':
            case '\u00A3':
            case '\u00A4':
            case '\u00A5':
            case '\u00A6':
            case '\u00A7':
            case '\u00A8':
            case '\u00A9':
            case '\u00AA':
            case '\u00AB':
            case '\u00AC':
            case '\u00AD':
            case '\u00AE':
            case '\u00AF':
            case '\u00B0':
            case '\u00B1':
            case '\u00B2':
            case '\u00B3':
            case '\u00B4':
            case '\u00B5':
            case '\u00B6':
            case '\u00B7':
            case '\u00B8':
            case '\u00B9':
            case '\u00BA':
            case '\u00BB':
            case '\u00BC':
            case '\u00BD':
            case '\u00BE':
            case '\u00BF':
            case '\u00C0':
            case '\u00C1':
            case '\u00C2':
            case '\u00C3':
            case '\u00C4':
            case '\u00C5':
            case '\u00C6':
            case '\u00C7':
            case '\u00C8':
            case '\u00C9':
            case '\u00CA':
            case '\u00CB':
            case '\u00CC':
            case '\u00CD':
            case '\u00CE':
            case '\u00CF':
            case '\u00D0':
            case '\u00D1':
            case '\u00D2':
            case '\u00D3':
            case '\u00D4':
            case '\u00D5':
            case '\u00D6':
            case '\u00D7':
            case '\u00D8':
            case '\u00D9':
            case '\u00DA':
            case '\u00DB':
            case '\u00DC':
            case '\u00DD':
            case '\u00DE':
            case '\u00DF':
            case '\u00E0':
            case '\u00E1':
            case '\u00E2':
            case '\u00E3':
            case '\u00E4':
            case '\u00E5':
            case '\u00E6':
            case '\u00E7':
            case '\u00E8':
            case '\u00E9':
            case '\u00EA':
            case '\u00EB':
            case '\u00EC':
            case '\u00ED':
            case '\u00EE':
            case '\u00EF':
            case '\u00F0':
            case '\u00F1':
            case '\u00F2':
            case '\u00F3':
            case '\u00F4':
            case '\u00F5':
            case '\u00F6':
            case '\u00F7':
            case '\u00F8':
            case '\u00F9':
            case '\u00FA':
            case '\u00FB':
            case '\u00FC':
            case '\u00FD':
            case '\u00FE':
            case '\u00FF':
                {
                alt27=4;
                }
                break;
            case '\\':
                {
                alt27=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // InternalPoosl.g:8870:39: '\\t'
                    {
                    match('\t'); 

                    }
                    break;
                case 2 :
                    // InternalPoosl.g:8870:44: ' ' .. '!'
                    {
                    matchRange(' ','!'); 

                    }
                    break;
                case 3 :
                    // InternalPoosl.g:8870:53: '#' .. '['
                    {
                    matchRange('#','['); 

                    }
                    break;
                case 4 :
                    // InternalPoosl.g:8870:62: ']' .. '\\u00FF'
                    {
                    matchRange(']','\u00FF'); 

                    }
                    break;
                case 5 :
                    // InternalPoosl.g:8870:76: RULE_ESCAPE_SEQUENCE
                    {
                    mRULE_ESCAPE_SEQUENCE(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_POOSL_STRING_ELEMENT"

    // $ANTLR start "RULE_ESCAPE_SEQUENCE"
    public final void mRULE_ESCAPE_SEQUENCE() throws RecognitionException {
        try {
            // InternalPoosl.g:8872:31: ( ( '\\\\x' '0' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) | '\\\\x' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )? | '\\\\n' | '\\\\t' | '\\\\v' | '\\\\b' | '\\\\r' | '\\\\f' | '\\\\a' | '\\\\\\\\' | '\\\\?' | '\\\\\\'' | '\\\\\"' ) )
            // InternalPoosl.g:8872:33: ( '\\\\x' '0' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) | '\\\\x' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )? | '\\\\n' | '\\\\t' | '\\\\v' | '\\\\b' | '\\\\r' | '\\\\f' | '\\\\a' | '\\\\\\\\' | '\\\\?' | '\\\\\\'' | '\\\\\"' )
            {
            // InternalPoosl.g:8872:33: ( '\\\\x' '0' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) | '\\\\x' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )? | '\\\\n' | '\\\\t' | '\\\\v' | '\\\\b' | '\\\\r' | '\\\\f' | '\\\\a' | '\\\\\\\\' | '\\\\?' | '\\\\\\'' | '\\\\\"' )
            int alt29=13;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // InternalPoosl.g:8872:34: '\\\\x' '0' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
                    {
                    match("\\x"); 

                    match('0'); 
                    if ( (input.LA(1)>='1' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // InternalPoosl.g:8872:73: '\\\\x' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )?
                    {
                    match("\\x"); 

                    if ( (input.LA(1)>='1' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // InternalPoosl.g:8872:108: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( ((LA28_0>='0' && LA28_0<='9')||(LA28_0>='A' && LA28_0<='F')||(LA28_0>='a' && LA28_0<='f')) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // InternalPoosl.g:
                            {
                            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;}


                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // InternalPoosl.g:8872:138: '\\\\n'
                    {
                    match("\\n"); 


                    }
                    break;
                case 4 :
                    // InternalPoosl.g:8872:144: '\\\\t'
                    {
                    match("\\t"); 


                    }
                    break;
                case 5 :
                    // InternalPoosl.g:8872:150: '\\\\v'
                    {
                    match("\\v"); 


                    }
                    break;
                case 6 :
                    // InternalPoosl.g:8872:156: '\\\\b'
                    {
                    match("\\b"); 


                    }
                    break;
                case 7 :
                    // InternalPoosl.g:8872:162: '\\\\r'
                    {
                    match("\\r"); 


                    }
                    break;
                case 8 :
                    // InternalPoosl.g:8872:168: '\\\\f'
                    {
                    match("\\f"); 


                    }
                    break;
                case 9 :
                    // InternalPoosl.g:8872:174: '\\\\a'
                    {
                    match("\\a"); 


                    }
                    break;
                case 10 :
                    // InternalPoosl.g:8872:180: '\\\\\\\\'
                    {
                    match("\\\\"); 


                    }
                    break;
                case 11 :
                    // InternalPoosl.g:8872:187: '\\\\?'
                    {
                    match("\\?"); 


                    }
                    break;
                case 12 :
                    // InternalPoosl.g:8872:193: '\\\\\\''
                    {
                    match("\\'"); 


                    }
                    break;
                case 13 :
                    // InternalPoosl.g:8872:200: '\\\\\"'
                    {
                    match("\\\""); 


                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_ESCAPE_SEQUENCE"

    // $ANTLR start "RULE_ESCAPE_ZERO"
    public final void mRULE_ESCAPE_ZERO() throws RecognitionException {
        try {
            // InternalPoosl.g:8874:27: ( '\\\\x' '0' ( '0' )? )
            // InternalPoosl.g:8874:29: '\\\\x' '0' ( '0' )?
            {
            match("\\x"); 

            match('0'); 
            // InternalPoosl.g:8874:39: ( '0' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0=='0') ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalPoosl.g:8874:39: '0'
                    {
                    match('0'); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_ESCAPE_ZERO"

    public void mTokens() throws RecognitionException {
        // InternalPoosl.g:1:8: ( T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | RULE_WS | RULE_SL_COMMENT | RULE_ML_COMMENT | RULE_BINARY_CORE | RULE_HEXADECIMAL_CORE | RULE_DECIMAL_CORE | RULE_REAL_CORE | RULE_FLOAT_CORE | RULE_IDENTIFIER_CORE | RULE_CHARACTER | RULE_POOSL_STRING | RULE_ENVIRONMENT_VARIABLE_NAME )
        int alt31=87;
        alt31 = dfa31.predict(input);
        switch (alt31) {
            case 1 :
                // InternalPoosl.g:1:10: T__25
                {
                mT__25(); 

                }
                break;
            case 2 :
                // InternalPoosl.g:1:16: T__26
                {
                mT__26(); 

                }
                break;
            case 3 :
                // InternalPoosl.g:1:22: T__27
                {
                mT__27(); 

                }
                break;
            case 4 :
                // InternalPoosl.g:1:28: T__28
                {
                mT__28(); 

                }
                break;
            case 5 :
                // InternalPoosl.g:1:34: T__29
                {
                mT__29(); 

                }
                break;
            case 6 :
                // InternalPoosl.g:1:40: T__30
                {
                mT__30(); 

                }
                break;
            case 7 :
                // InternalPoosl.g:1:46: T__31
                {
                mT__31(); 

                }
                break;
            case 8 :
                // InternalPoosl.g:1:52: T__32
                {
                mT__32(); 

                }
                break;
            case 9 :
                // InternalPoosl.g:1:58: T__33
                {
                mT__33(); 

                }
                break;
            case 10 :
                // InternalPoosl.g:1:64: T__34
                {
                mT__34(); 

                }
                break;
            case 11 :
                // InternalPoosl.g:1:70: T__35
                {
                mT__35(); 

                }
                break;
            case 12 :
                // InternalPoosl.g:1:76: T__36
                {
                mT__36(); 

                }
                break;
            case 13 :
                // InternalPoosl.g:1:82: T__37
                {
                mT__37(); 

                }
                break;
            case 14 :
                // InternalPoosl.g:1:88: T__38
                {
                mT__38(); 

                }
                break;
            case 15 :
                // InternalPoosl.g:1:94: T__39
                {
                mT__39(); 

                }
                break;
            case 16 :
                // InternalPoosl.g:1:100: T__40
                {
                mT__40(); 

                }
                break;
            case 17 :
                // InternalPoosl.g:1:106: T__41
                {
                mT__41(); 

                }
                break;
            case 18 :
                // InternalPoosl.g:1:112: T__42
                {
                mT__42(); 

                }
                break;
            case 19 :
                // InternalPoosl.g:1:118: T__43
                {
                mT__43(); 

                }
                break;
            case 20 :
                // InternalPoosl.g:1:124: T__44
                {
                mT__44(); 

                }
                break;
            case 21 :
                // InternalPoosl.g:1:130: T__45
                {
                mT__45(); 

                }
                break;
            case 22 :
                // InternalPoosl.g:1:136: T__46
                {
                mT__46(); 

                }
                break;
            case 23 :
                // InternalPoosl.g:1:142: T__47
                {
                mT__47(); 

                }
                break;
            case 24 :
                // InternalPoosl.g:1:148: T__48
                {
                mT__48(); 

                }
                break;
            case 25 :
                // InternalPoosl.g:1:154: T__49
                {
                mT__49(); 

                }
                break;
            case 26 :
                // InternalPoosl.g:1:160: T__50
                {
                mT__50(); 

                }
                break;
            case 27 :
                // InternalPoosl.g:1:166: T__51
                {
                mT__51(); 

                }
                break;
            case 28 :
                // InternalPoosl.g:1:172: T__52
                {
                mT__52(); 

                }
                break;
            case 29 :
                // InternalPoosl.g:1:178: T__53
                {
                mT__53(); 

                }
                break;
            case 30 :
                // InternalPoosl.g:1:184: T__54
                {
                mT__54(); 

                }
                break;
            case 31 :
                // InternalPoosl.g:1:190: T__55
                {
                mT__55(); 

                }
                break;
            case 32 :
                // InternalPoosl.g:1:196: T__56
                {
                mT__56(); 

                }
                break;
            case 33 :
                // InternalPoosl.g:1:202: T__57
                {
                mT__57(); 

                }
                break;
            case 34 :
                // InternalPoosl.g:1:208: T__58
                {
                mT__58(); 

                }
                break;
            case 35 :
                // InternalPoosl.g:1:214: T__59
                {
                mT__59(); 

                }
                break;
            case 36 :
                // InternalPoosl.g:1:220: T__60
                {
                mT__60(); 

                }
                break;
            case 37 :
                // InternalPoosl.g:1:226: T__61
                {
                mT__61(); 

                }
                break;
            case 38 :
                // InternalPoosl.g:1:232: T__62
                {
                mT__62(); 

                }
                break;
            case 39 :
                // InternalPoosl.g:1:238: T__63
                {
                mT__63(); 

                }
                break;
            case 40 :
                // InternalPoosl.g:1:244: T__64
                {
                mT__64(); 

                }
                break;
            case 41 :
                // InternalPoosl.g:1:250: T__65
                {
                mT__65(); 

                }
                break;
            case 42 :
                // InternalPoosl.g:1:256: T__66
                {
                mT__66(); 

                }
                break;
            case 43 :
                // InternalPoosl.g:1:262: T__67
                {
                mT__67(); 

                }
                break;
            case 44 :
                // InternalPoosl.g:1:268: T__68
                {
                mT__68(); 

                }
                break;
            case 45 :
                // InternalPoosl.g:1:274: T__69
                {
                mT__69(); 

                }
                break;
            case 46 :
                // InternalPoosl.g:1:280: T__70
                {
                mT__70(); 

                }
                break;
            case 47 :
                // InternalPoosl.g:1:286: T__71
                {
                mT__71(); 

                }
                break;
            case 48 :
                // InternalPoosl.g:1:292: T__72
                {
                mT__72(); 

                }
                break;
            case 49 :
                // InternalPoosl.g:1:298: T__73
                {
                mT__73(); 

                }
                break;
            case 50 :
                // InternalPoosl.g:1:304: T__74
                {
                mT__74(); 

                }
                break;
            case 51 :
                // InternalPoosl.g:1:310: T__75
                {
                mT__75(); 

                }
                break;
            case 52 :
                // InternalPoosl.g:1:316: T__76
                {
                mT__76(); 

                }
                break;
            case 53 :
                // InternalPoosl.g:1:322: T__77
                {
                mT__77(); 

                }
                break;
            case 54 :
                // InternalPoosl.g:1:328: T__78
                {
                mT__78(); 

                }
                break;
            case 55 :
                // InternalPoosl.g:1:334: T__79
                {
                mT__79(); 

                }
                break;
            case 56 :
                // InternalPoosl.g:1:340: T__80
                {
                mT__80(); 

                }
                break;
            case 57 :
                // InternalPoosl.g:1:346: T__81
                {
                mT__81(); 

                }
                break;
            case 58 :
                // InternalPoosl.g:1:352: T__82
                {
                mT__82(); 

                }
                break;
            case 59 :
                // InternalPoosl.g:1:358: T__83
                {
                mT__83(); 

                }
                break;
            case 60 :
                // InternalPoosl.g:1:364: T__84
                {
                mT__84(); 

                }
                break;
            case 61 :
                // InternalPoosl.g:1:370: T__85
                {
                mT__85(); 

                }
                break;
            case 62 :
                // InternalPoosl.g:1:376: T__86
                {
                mT__86(); 

                }
                break;
            case 63 :
                // InternalPoosl.g:1:382: T__87
                {
                mT__87(); 

                }
                break;
            case 64 :
                // InternalPoosl.g:1:388: T__88
                {
                mT__88(); 

                }
                break;
            case 65 :
                // InternalPoosl.g:1:394: T__89
                {
                mT__89(); 

                }
                break;
            case 66 :
                // InternalPoosl.g:1:400: T__90
                {
                mT__90(); 

                }
                break;
            case 67 :
                // InternalPoosl.g:1:406: T__91
                {
                mT__91(); 

                }
                break;
            case 68 :
                // InternalPoosl.g:1:412: T__92
                {
                mT__92(); 

                }
                break;
            case 69 :
                // InternalPoosl.g:1:418: T__93
                {
                mT__93(); 

                }
                break;
            case 70 :
                // InternalPoosl.g:1:424: T__94
                {
                mT__94(); 

                }
                break;
            case 71 :
                // InternalPoosl.g:1:430: T__95
                {
                mT__95(); 

                }
                break;
            case 72 :
                // InternalPoosl.g:1:436: T__96
                {
                mT__96(); 

                }
                break;
            case 73 :
                // InternalPoosl.g:1:442: T__97
                {
                mT__97(); 

                }
                break;
            case 74 :
                // InternalPoosl.g:1:448: T__98
                {
                mT__98(); 

                }
                break;
            case 75 :
                // InternalPoosl.g:1:454: T__99
                {
                mT__99(); 

                }
                break;
            case 76 :
                // InternalPoosl.g:1:460: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 77 :
                // InternalPoosl.g:1:468: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 78 :
                // InternalPoosl.g:1:484: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 79 :
                // InternalPoosl.g:1:500: RULE_BINARY_CORE
                {
                mRULE_BINARY_CORE(); 

                }
                break;
            case 80 :
                // InternalPoosl.g:1:517: RULE_HEXADECIMAL_CORE
                {
                mRULE_HEXADECIMAL_CORE(); 

                }
                break;
            case 81 :
                // InternalPoosl.g:1:539: RULE_DECIMAL_CORE
                {
                mRULE_DECIMAL_CORE(); 

                }
                break;
            case 82 :
                // InternalPoosl.g:1:557: RULE_REAL_CORE
                {
                mRULE_REAL_CORE(); 

                }
                break;
            case 83 :
                // InternalPoosl.g:1:572: RULE_FLOAT_CORE
                {
                mRULE_FLOAT_CORE(); 

                }
                break;
            case 84 :
                // InternalPoosl.g:1:588: RULE_IDENTIFIER_CORE
                {
                mRULE_IDENTIFIER_CORE(); 

                }
                break;
            case 85 :
                // InternalPoosl.g:1:609: RULE_CHARACTER
                {
                mRULE_CHARACTER(); 

                }
                break;
            case 86 :
                // InternalPoosl.g:1:624: RULE_POOSL_STRING
                {
                mRULE_POOSL_STRING(); 

                }
                break;
            case 87 :
                // InternalPoosl.g:1:642: RULE_ENVIRONMENT_VARIABLE_NAME
                {
                mRULE_ENVIRONMENT_VARIABLE_NAME(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA26 dfa26 = new DFA26(this);
    protected DFA29 dfa29 = new DFA29(this);
    protected DFA31 dfa31 = new DFA31(this);
    static final String DFA8_eotS =
        "\6\uffff";
    static final String DFA8_eofS =
        "\6\uffff";
    static final String DFA8_minS =
        "\2\0\4\uffff";
    static final String DFA8_maxS =
        "\2\uffff\4\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\1\1\2\1\4\1\3";
    static final String DFA8_specialS =
        "\1\0\1\1\4\uffff}>";
    static final String[] DFA8_transitionS = DFA8_transitionS_.DFA8_transitionS;
    private static final class DFA8_transitionS_ {
        static final String[] DFA8_transitionS = {
                "\52\2\1\1\4\2\1\3\uffd0\2",
                "\52\5\1\1\4\5\1\4\uffd0\5",
                "",
                "",
                "",
                ""
        };
    }

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    static class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "()* loopback of 8844:26: ( RULE_MLC_ANY | RULE_MLC_SLASH | RULE_MLC_STAR )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_0 = input.LA(1);

                        s = -1;
                        if ( (LA8_0=='*') ) {s = 1;}

                        else if ( ((LA8_0>='\u0000' && LA8_0<=')')||(LA8_0>='+' && LA8_0<='.')||(LA8_0>='0' && LA8_0<='\uFFFF')) ) {s = 2;}

                        else if ( (LA8_0=='/') ) {s = 3;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA8_1 = input.LA(1);

                        s = -1;
                        if ( (LA8_1=='/') ) {s = 4;}

                        else if ( (LA8_1=='*') ) {s = 1;}

                        else if ( ((LA8_1>='\u0000' && LA8_1<=')')||(LA8_1>='+' && LA8_1<='.')||(LA8_1>='0' && LA8_1<='\uFFFF')) ) {s = 5;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA26_eotS =
        "\10\uffff\1\11\1\uffff";
    static final String DFA26_eofS =
        "\12\uffff";
    static final String DFA26_minS =
        "\1\11\4\uffff\1\42\1\60\1\uffff\1\61\1\uffff";
    static final String DFA26_maxS =
        "\1\u00ff\4\uffff\1\170\1\146\1\uffff\1\146\1\uffff";
    static final String DFA26_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\5\1\uffff\1\6";
    static final String DFA26_specialS =
        "\12\uffff}>";
    static final String[] DFA26_transitionS = {
            "\1\1\26\uffff\7\2\1\uffff\64\3\1\5\u00a3\4",
            "",
            "",
            "",
            "",
            "\1\7\4\uffff\1\7\27\uffff\1\7\34\uffff\1\7\4\uffff\2\7\3\uffff\1\7\7\uffff\1\7\3\uffff\1\7\1\uffff\1\7\1\uffff\1\7\1\uffff\1\6",
            "\1\10\11\7\7\uffff\6\7\32\uffff\6\7",
            "",
            "\11\7\7\uffff\6\7\32\uffff\6\7",
            ""
    };

    static final short[] DFA26_eot = DFA.unpackEncodedString(DFA26_eotS);
    static final short[] DFA26_eof = DFA.unpackEncodedString(DFA26_eofS);
    static final char[] DFA26_min = DFA.unpackEncodedStringToUnsignedChars(DFA26_minS);
    static final char[] DFA26_max = DFA.unpackEncodedStringToUnsignedChars(DFA26_maxS);
    static final short[] DFA26_accept = DFA.unpackEncodedString(DFA26_acceptS);
    static final short[] DFA26_special = DFA.unpackEncodedString(DFA26_specialS);
    static final short[][] DFA26_transition;

    static {
        int numStates = DFA26_transitionS.length;
        DFA26_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA26_transition[i] = DFA.unpackEncodedString(DFA26_transitionS[i]);
        }
    }

    class DFA26 extends DFA {

        public DFA26(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 26;
            this.eot = DFA26_eot;
            this.eof = DFA26_eof;
            this.min = DFA26_min;
            this.max = DFA26_max;
            this.accept = DFA26_accept;
            this.special = DFA26_special;
            this.transition = DFA26_transition;
        }
        public String getDescription() {
            return "8868:35: ( '\\t' | ' ' .. '&' | '(' .. '[' | ']' .. '\\u00FF' | RULE_ESCAPE_SEQUENCE | RULE_ESCAPE_ZERO )";
        }
    }
    static final String DFA29_eotS =
        "\20\uffff";
    static final String DFA29_eofS =
        "\20\uffff";
    static final String DFA29_minS =
        "\1\134\1\42\1\60\15\uffff";
    static final String DFA29_maxS =
        "\1\134\1\170\1\146\15\uffff";
    static final String DFA29_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\1\1\2";
    static final String DFA29_specialS =
        "\20\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\1",
            "\1\15\4\uffff\1\14\27\uffff\1\13\34\uffff\1\12\4\uffff\1\11\1\6\3\uffff\1\10\7\uffff\1\3\3\uffff\1\7\1\uffff\1\4\1\uffff\1\5\1\uffff\1\2",
            "\1\16\11\17\7\uffff\6\17\32\uffff\6\17",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
    static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
    static final char[] DFA29_min = DFA.unpackEncodedStringToUnsignedChars(DFA29_minS);
    static final char[] DFA29_max = DFA.unpackEncodedStringToUnsignedChars(DFA29_maxS);
    static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
    static final short[] DFA29_special = DFA.unpackEncodedString(DFA29_specialS);
    static final short[][] DFA29_transition;

    static {
        int numStates = DFA29_transitionS.length;
        DFA29_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
        }
    }

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = DFA29_eot;
            this.eof = DFA29_eof;
            this.min = DFA29_min;
            this.max = DFA29_max;
            this.accept = DFA29_accept;
            this.special = DFA29_special;
            this.transition = DFA29_transition;
        }
        public String getDescription() {
            return "8872:33: ( '\\\\x' '0' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) | '\\\\x' ( '1' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )? | '\\\\n' | '\\\\t' | '\\\\v' | '\\\\b' | '\\\\r' | '\\\\f' | '\\\\a' | '\\\\\\\\' | '\\\\?' | '\\\\\\'' | '\\\\\"' )";
        }
    }
    static final String DFA31_eotS =
        "\1\uffff\6\53\1\uffff\1\53\2\uffff\1\101\2\uffff\1\53\1\uffff\1\106\1\53\2\uffff\1\114\1\uffff\1\53\1\uffff\4\53\2\uffff\2\53\2\uffff\1\133\1\135\1\137\2\uffff\1\142\1\uffff\2\145\4\uffff\2\53\1\155\4\53\1\163\11\53\2\uffff\3\53\1\u0084\1\uffff\4\53\1\u008a\1\uffff\4\53\1\u0090\3\53\1\u0094\1\u0095\3\53\14\uffff\1\u008a\1\145\4\53\1\u009e\1\uffff\1\53\1\u00a0\1\u00a1\1\u00a2\1\53\1\uffff\16\53\1\u00b2\2\uffff\2\53\1\u00b6\1\53\3\uffff\1\53\1\u00bb\2\53\1\uffff\3\53\2\uffff\1\u00c1\1\53\1\u00c3\1\u008a\1\53\1\u00c5\2\53\1\uffff\1\53\3\uffff\1\u00c9\5\53\1\u00cf\2\53\1\u00d2\5\53\1\uffff\2\53\1\u00da\1\uffff\1\u00db\1\uffff\1\u008a\1\53\1\uffff\1\u00dd\1\u00de\2\53\1\u00e1\1\uffff\1\53\1\uffff\1\53\1\uffff\3\53\1\uffff\1\53\1\u00e8\1\u00e9\2\53\1\uffff\2\53\1\uffff\4\53\1\u00f2\2\53\2\uffff\1\53\2\uffff\1\u00f6\1\u00f7\1\uffff\1\u00f8\1\u00fa\2\53\1\u00fd\1\53\2\uffff\10\53\1\uffff\1\u0107\1\u0108\1\u0109\3\uffff\1\53\1\uffff\2\53\1\uffff\1\u010d\1\u010e\2\53\1\u0111\1\53\1\u0113\1\53\1\u0115\3\uffff\3\53\2\uffff\1\u0119\1\53\1\uffff\1\53\1\uffff\1\u011c\1\uffff\1\u011d\1\u011e\1\u011f\1\uffff\1\53\1\u0121\4\uffff\1\53\1\uffff\1\u0123\1\uffff";
    static final String DFA31_eofS =
        "\u0124\uffff";
    static final String DFA31_minS =
        "\1\11\1\146\3\141\1\154\1\141\1\uffff\1\145\2\uffff\1\75\2\uffff\1\141\1\uffff\1\75\1\145\2\uffff\1\60\1\uffff\1\141\1\uffff\1\150\1\141\1\150\1\144\2\uffff\1\142\1\145\2\uffff\3\75\2\uffff\1\52\1\uffff\2\56\4\uffff\1\160\1\146\1\60\1\156\1\154\1\167\1\164\1\60\1\146\2\141\1\163\1\162\1\164\1\163\1\162\1\163\2\uffff\1\157\2\162\1\75\1\uffff\1\163\1\151\1\154\1\151\1\60\1\uffff\1\164\1\160\1\145\1\165\1\60\1\154\1\151\1\164\2\60\1\144\1\157\1\163\14\uffff\1\60\1\56\1\157\2\164\1\145\1\60\1\uffff\1\151\3\60\1\141\1\uffff\2\141\2\163\1\156\1\145\1\162\2\145\1\151\1\150\1\163\1\143\1\164\1\60\2\uffff\2\164\1\60\1\160\1\53\2\uffff\1\165\1\60\1\156\1\145\1\uffff\1\163\1\154\1\150\2\uffff\1\60\1\162\2\60\1\162\1\60\1\141\1\162\1\uffff\1\166\3\uffff\1\60\1\165\1\171\1\163\1\164\1\156\1\60\1\145\1\156\1\60\1\141\1\157\1\141\1\145\1\163\1\uffff\1\145\1\143\1\60\1\uffff\3\60\1\162\1\uffff\2\60\2\145\1\60\1\uffff\1\164\1\uffff\1\164\1\uffff\1\156\1\162\1\145\1\uffff\1\154\2\60\2\145\1\uffff\1\156\1\144\1\uffff\1\142\1\144\1\147\1\163\1\60\1\155\1\150\2\uffff\1\156\2\uffff\2\60\1\uffff\2\60\1\143\1\165\1\60\1\164\2\uffff\1\162\1\154\1\164\1\163\1\154\1\163\1\145\1\163\1\uffff\3\60\3\uffff\1\151\1\uffff\1\145\1\160\1\uffff\2\60\1\163\1\124\1\60\1\145\1\60\1\163\1\60\3\uffff\1\142\1\163\1\164\2\uffff\1\60\1\151\1\uffff\1\163\1\uffff\1\60\1\uffff\3\60\1\uffff\1\155\1\60\4\uffff\1\145\1\uffff\1\60\1\uffff";
    static final String DFA31_maxS =
        "\1\175\1\156\1\151\1\157\1\165\1\170\1\141\1\uffff\1\145\2\uffff\1\75\2\uffff\1\162\1\uffff\1\75\1\171\2\uffff\1\71\1\uffff\1\145\1\uffff\1\162\2\151\1\162\2\uffff\1\156\1\145\2\uffff\3\75\2\uffff\1\57\1\uffff\1\170\1\71\4\uffff\1\160\1\164\1\172\1\164\1\154\1\167\1\164\1\172\1\154\1\165\1\141\1\163\1\162\1\164\1\163\1\162\1\164\2\uffff\1\157\2\162\1\75\1\uffff\1\163\1\151\1\154\1\151\1\146\1\uffff\1\164\1\160\1\145\1\165\1\172\1\154\1\151\1\164\2\172\1\144\1\157\1\163\14\uffff\1\146\1\71\1\157\2\164\1\145\1\172\1\uffff\1\151\3\172\1\141\1\uffff\2\141\2\163\1\156\1\145\1\162\2\145\1\151\1\150\1\163\1\143\1\164\1\172\2\uffff\2\164\1\172\1\160\1\71\2\uffff\1\165\1\172\1\156\1\145\1\uffff\1\163\1\154\1\150\2\uffff\1\172\1\162\1\172\1\146\1\162\1\172\1\141\1\162\1\uffff\1\166\3\uffff\1\172\1\165\1\171\1\163\1\164\1\156\1\172\1\145\1\156\1\172\1\141\1\157\1\141\1\145\1\163\1\uffff\1\145\1\143\1\172\1\uffff\1\172\1\71\1\146\1\162\1\uffff\2\172\2\145\1\172\1\uffff\1\164\1\uffff\1\164\1\uffff\1\156\1\162\1\145\1\uffff\1\154\2\172\2\145\1\uffff\1\156\1\144\1\uffff\1\142\1\144\1\147\1\163\1\172\1\155\1\150\2\uffff\1\156\2\uffff\2\172\1\uffff\2\172\1\143\1\165\1\172\1\164\2\uffff\1\162\1\154\1\164\1\163\1\154\1\163\1\145\1\163\1\uffff\3\172\3\uffff\1\151\1\uffff\1\145\1\160\1\uffff\2\172\1\163\1\124\1\172\1\145\1\172\1\163\1\172\3\uffff\1\142\1\163\1\164\2\uffff\1\172\1\151\1\uffff\1\163\1\uffff\1\172\1\uffff\3\172\1\uffff\1\155\1\172\4\uffff\1\145\1\uffff\1\172\1\uffff";
    static final String DFA31_acceptS =
        "\7\uffff\1\10\1\uffff\1\12\1\13\1\uffff\1\15\1\16\1\uffff\1\23\2\uffff\1\32\1\33\1\uffff\1\35\1\uffff\1\37\4\uffff\1\62\1\63\2\uffff\1\75\1\76\3\uffff\1\111\1\112\1\uffff\1\114\2\uffff\1\124\1\125\1\126\1\127\21\uffff\1\31\1\14\4\uffff\1\24\5\uffff\1\34\15\uffff\1\103\1\101\1\106\1\105\1\110\1\107\1\115\1\116\1\113\1\117\1\120\1\121\7\uffff\1\40\5\uffff\1\45\17\uffff\1\104\1\102\5\uffff\1\122\1\123\4\uffff\1\43\3\uffff\1\46\1\70\10\uffff\1\100\1\uffff\1\77\1\56\1\57\17\uffff\1\64\3\uffff\1\67\4\uffff\1\66\5\uffff\1\65\1\uffff\1\71\1\uffff\1\22\3\uffff\1\4\5\uffff\1\51\2\uffff\1\42\7\uffff\1\53\1\61\1\uffff\1\41\1\54\2\uffff\1\73\6\uffff\1\60\1\5\10\uffff\1\20\3\uffff\1\55\1\44\1\72\1\uffff\1\1\2\uffff\1\3\11\uffff\1\25\1\47\1\36\3\uffff\1\50\1\30\2\uffff\1\6\1\uffff\1\11\1\uffff\1\17\3\uffff\1\27\2\uffff\1\21\1\2\1\26\1\74\1\uffff\1\7\1\uffff\1\52";
    static final String DFA31_specialS =
        "\u0124\uffff}>";
    static final String[] DFA31_transitionS = {
            "\2\50\2\uffff\1\50\22\uffff\1\50\1\20\1\55\1\uffff\1\56\1\uffff\1\45\1\54\1\11\1\12\1\46\1\41\1\7\1\40\1\24\1\47\1\51\11\52\1\13\1\25\1\43\1\42\1\44\1\17\1\15\32\53\1\34\1\uffff\1\35\1\27\2\uffff\1\36\1\53\1\4\1\3\1\5\1\31\2\53\1\1\2\53\1\37\1\10\1\2\1\33\1\16\1\53\1\26\1\21\1\30\1\53\1\6\1\32\3\53\1\22\1\14\1\23",
            "\1\61\6\uffff\1\57\1\60",
            "\1\62\3\uffff\1\64\3\uffff\1\63",
            "\1\65\3\uffff\1\67\11\uffff\1\66",
            "\1\72\6\uffff\1\71\3\uffff\1\70\10\uffff\1\73",
            "\1\75\13\uffff\1\74",
            "\1\76",
            "",
            "\1\77",
            "",
            "",
            "\1\100",
            "",
            "",
            "\1\104\15\uffff\1\103\2\uffff\1\102",
            "",
            "\1\105",
            "\1\111\5\uffff\1\112\13\uffff\1\110\1\uffff\1\107",
            "",
            "",
            "\12\113",
            "",
            "\1\116\3\uffff\1\115",
            "",
            "\1\117\11\uffff\1\120",
            "\1\122\7\uffff\1\121",
            "\1\123\1\124",
            "\1\125\15\uffff\1\126",
            "",
            "",
            "\1\130\13\uffff\1\127",
            "\1\131",
            "",
            "",
            "\1\132",
            "\1\134",
            "\1\136",
            "",
            "",
            "\1\141\4\uffff\1\140",
            "",
            "\1\146\23\uffff\1\143\25\uffff\1\144\11\uffff\1\143\25\uffff\1\144",
            "\1\146\1\uffff\12\147",
            "",
            "",
            "",
            "",
            "\1\150",
            "\1\154\2\uffff\1\151\11\uffff\1\152\1\153",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\157\5\uffff\1\156",
            "\1\160",
            "\1\161",
            "\1\162",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\164\5\uffff\1\165",
            "\1\166\23\uffff\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\177\1\176",
            "",
            "",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\12\113\13\uffff\1\u0089\1\u008b\36\uffff\1\u0089\1\u008b",
            "",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\u0099\13\uffff\1\u0089\1\u008b\36\uffff\1\u0089\1\u008b",
            "\1\146\1\uffff\12\147",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u009f",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00a3",
            "",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "\1\u00b3",
            "\1\u00b4",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\5\53\1\u00b5\24\53",
            "\1\u00b7",
            "\1\u00b8\1\uffff\1\u00b8\2\uffff\12\u00b9",
            "",
            "",
            "\1\u00ba",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00bc",
            "\1\u00bd",
            "",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00c2",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\u0099\13\uffff\1\u0089\1\u008b\36\uffff\1\u0089\1\u008b",
            "\1\u00c4",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00c6",
            "\1\u00c7",
            "",
            "\1\u00c8",
            "",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00d0",
            "\1\u00d1",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00d3",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "",
            "\1\u00d8",
            "\1\u00d9",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\u00b9",
            "\12\u00b9\14\uffff\1\u008b\37\uffff\1\u008b",
            "\1\u00dc",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00df",
            "\1\u00e0",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u00e2",
            "",
            "\1\u00e3",
            "",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "",
            "\1\u00e7",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00ea",
            "\1\u00eb",
            "",
            "\1\u00ec",
            "\1\u00ed",
            "",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00f3",
            "\1\u00f4",
            "",
            "",
            "\1\u00f5",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\13\53\1\u00f9\16\53",
            "\1\u00fb",
            "\1\u00fc",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u00fe",
            "",
            "",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "",
            "\1\u010a",
            "",
            "\1\u010b",
            "\1\u010c",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u010f",
            "\1\u0110",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0112",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u0114",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "",
            "\1\u0116",
            "\1\u0117",
            "\1\u0118",
            "",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\1\u011a",
            "",
            "\1\u011b",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "\1\u0120",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            "",
            "",
            "",
            "",
            "\1\u0122",
            "",
            "\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32\53",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | RULE_WS | RULE_SL_COMMENT | RULE_ML_COMMENT | RULE_BINARY_CORE | RULE_HEXADECIMAL_CORE | RULE_DECIMAL_CORE | RULE_REAL_CORE | RULE_FLOAT_CORE | RULE_IDENTIFIER_CORE | RULE_CHARACTER | RULE_POOSL_STRING | RULE_ENVIRONMENT_VARIABLE_NAME );";
        }
    }
 

}