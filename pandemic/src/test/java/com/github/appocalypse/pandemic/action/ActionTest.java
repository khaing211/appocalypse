package com.github.appocalypse.pandemic.action;

import com.github.appocalypse.pandemic.Keyword;
import org.junit.Test;

import static com.github.appocalypse.pandemic.token.Tokens.tokens;
import static com.github.appocalypse.pandemic.token.Tokens.of;

public class ActionTest {
    @Test
    public void testNext() {
        Action action = new Action("build", ActionStrategy.nullActionStragety(), tokens(of(Keyword.BUILD)));
        System.out.println(action.next("b"));
    }
}
