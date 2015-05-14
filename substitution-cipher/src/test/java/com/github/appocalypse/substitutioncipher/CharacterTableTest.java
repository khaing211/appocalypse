package com.github.appocalypse.substitutioncipher;

import org.junit.Test;

public class CharacterTableTest {
    @Test
    public void testMapZ() {
        CharacterTable table = new CharacterTable();
        table.map('z', 'z');
    }
}
