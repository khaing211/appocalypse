package com.github.appocalypse;

import org.junit.Test;

import java.io.ByteArrayInputStream;

/**
 * Created by developer on 2/21/15.
 */
public class JsonGrepTest {
    @Test
    public void testRoot() {
        setSystemIn("[]");

        JsonGrep.main(new String[] { "$"} );
    }

    private void setSystemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

    }
}
