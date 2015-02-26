package com.github.appcalypse.jdk.extra.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EitherTest {
    @Test
    public void testOrLeft() {
        String value = Either.<Exception, String>ofThrowable(new Exception()).orLeft("left");
        assertEquals("left", value);
    }

}
