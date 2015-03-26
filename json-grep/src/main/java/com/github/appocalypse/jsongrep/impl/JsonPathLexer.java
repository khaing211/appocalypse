package com.github.appocalypse.jsongrep.impl;

import javax.json.Json;

public class JsonPathLexer {

    final private static char[] SPECIAL_CHARS = new char[] { '.', '*', '[', ']', '(', ')', '?', ':', '$', '@', '\'', '<', '=', '>' };

    final private String pattern;

    private boolean spaceSensitive = true;

    private int rewindIndex;
    private int index;

    public JsonPathLexer(String pattern) {
        this(pattern, 0);
    }

    public JsonPathLexer(String pattern, int startIndex) {
        this.pattern = pattern;
        this.index = startIndex;
        this.rewindIndex = startIndex;
    }

    public void spaceSensitive(boolean spaceSensitive) {
        this.spaceSensitive = spaceSensitive;
    }

    /**
     * @return current index
     */
    public int index() {
        return index;
    }

    /**
     * rewind() to before last consumed token.
     * This method can only call once after nextToken()
     */
    public void rewind() {
        this.index = rewindIndex;
    }


    /**
     * peek the next token, current index does not change.
     * This operation has no side effect, you can call as many time as you want.
     *
     * @return token
     */
    public JsonPathToken peek() {
        final int currentRewindIndex = rewindIndex;
        final int currentIndex = index;

        final JsonPathToken token = nextToken();

        index = currentIndex;
        rewindIndex = currentRewindIndex;

        return token;
    }

    /**
     * Current index changes by next token
     *
     * @return token
     */
    public JsonPathToken nextToken() {
        // store the rewind index
        rewindIndex = index;

        if (index >= pattern.length()) {
            return JsonPathToken.eof(index);
        }

        if (spaceSensitive && Character.isSpaceChar(pattern.charAt(index))) {
            return space();
        }

        for (int i = index; i < pattern.length(); i++) {
            final char ch = pattern.charAt(i);

            final boolean special = inArray(ch, SPECIAL_CHARS);
            final boolean considerSpace = spaceSensitive && Character.isSpaceChar(ch);

            if (special || considerSpace) {
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

    private JsonPathToken space() {
        int i = index+1;

        for (; i < pattern.length(); i++) {
            final char ch = pattern.charAt(i);
            if (!Character.isSpaceChar(ch)) {
                break;
            }
        }

        final JsonPathToken token = new JsonPathToken.SpaceToken(index, pattern.substring(index, i));
        index = i;
        return token;
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
