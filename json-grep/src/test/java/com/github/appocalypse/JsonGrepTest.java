package com.github.appocalypse;

import org.junit.Test;

import java.io.ByteArrayInputStream;

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
