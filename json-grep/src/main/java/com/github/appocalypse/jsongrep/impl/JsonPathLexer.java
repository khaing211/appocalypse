package com.github.appocalypse.jsongrep.impl;

import javax.json.Json;

public class JsonPathLexer {

    final private static char[] SPECIAL_CHARS = new char[] { '.', '*', '[', ']', '(', ')', '?', ':', '$', '@', '\'', '<', '=', '>' };

    final private String pattern;
    private int index;

    public JsonPathLexer(String pattern) {
        this.pattern = pattern;
        this.index = 0;
    }

    public JsonPathLexer(String pattern, int startIndex) {
        this.pattern = pattern;
        this.index = startIndex;
    }

    /**
     * @return current index
     */
    public int index() {
        return index;
    }


    /**
     * peek the next token, current index does not change.
     * This operation has no side effect, you can call as many time as you want.
     *
     * @return token
     */
    public JsonPathToken peek() {
        final int currentIndex = index;
        final JsonPathToken token = nextToken();
        index = currentIndex;
        return token;
    }

    /**
     * Current index changes by next token
     *
     * @return token
     */
    public JsonPathToken nextToken() {
        if (index >= pattern.length()) {
            return JsonPathToken.eof(index);
        }

        for (int i = index; i < pattern.length(); i++) {
            final char ch = pattern.charAt(i);
            final boolean special = inArray(ch, SPECIAL_CHARS);

            if (special) {
                JsonPathToken ret = null;

                if (i == index) {
                    switch (ch) {
                        case '\'':
                            ret = JsonPathToken.quote(index);
                            index = i + 1;
                            break;
                        case '*':
                            ret = JsonPathToken.asterisk(index);
                            index = i + 1;
                            break;
                        case '[':
                            ret = JsonPathToken.openSquareBracket(index);
                            index = i + 1;
                            break;
                        case ']':
                            ret = JsonPathToken.closeSquareBracket(index);
                            index = i + 1;
                            break;
                        case ':':
                            ret = JsonPathToken.semiColon(index);
                            index = i + 1;
                            break;
                        case '?':
                            ret = JsonPathToken.question(index);
                            index = i + 1;
                            break;
                        case '$':
                            ret = JsonPathToken.dollarSign(index);
                            index = i + 1;
                            break;
                        case '@':
                            ret = JsonPathToken.at(index);
                            index = i + 1;
                            break;
                        case '(':
                            ret = JsonPathToken.openRoundBracket(index);
                            index = i + 1;
                            break;
                        case ')':
                            ret = JsonPathToken.closeRoundBracket(index);
                            index = i + 1;
                            break;
                        case '=':
                            ret = JsonPathToken.equal(index);
                            index = i + 1;
                            break;
                        case '<': {
                            if (i + 1 != pattern.length() && pattern.charAt(i+1) == '=') {
                                ret = JsonPathToken.lessThanOrEqual(index);
                                index = i + 2;
                            } else {
                                ret = JsonPathToken.lessThan(index);
                                index = i + 1;
                            }
                        } break;
                        case '>': {
                            if (i + 1 != pattern.length() && pattern.charAt(i+1) == '=') {
                                ret = JsonPathToken.greaterThanOrEqual(index);
                                index = i + 2;
                            } else {
                                ret = JsonPathToken.greaterThan(index);
                                index = i + 1;
                            }
                        } break;
                        case '.': {
                            if (i + 1 != pattern.length() && pattern.charAt(i+1) == '.') {
                                ret = JsonPathToken.twoDots(index);
                                index = i + 2;
                            } else {
                                ret = JsonPathToken.dot(index);
                                index = i + 1;
                            }
                        } break;
                        default:
                            // this should not happen
                            throw new IllegalStateException("unchecked special char " + ch + "at index " + index);
                    }
                } else {
                    ret = JsonPathToken.string(index, pattern.substring(index, i));
                    index = i;
                }

                return ret;
            }
        }

        JsonPathToken ret = JsonPathToken.string(index, pattern.substring(index));
        index = pattern.length();
        return ret;
    }

    private static boolean inArray(char needle, char[] haystack) {
        for (int i = 0; i < haystack.length; i++) {
            if (needle == haystack[i]) {
                return true;
            }
        }

        return false;
    }
}
