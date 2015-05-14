package com.github.appocalypse.substitutioncipher;

/**
 * Store mapping of character: encrypt to decrypt
 */
public class CharacterTable {
    // inclusive [a-z]
    private final Character[] table = new Character['z' - 'a' + 1];

    // copy constructor to clone CharacterTable
    public CharacterTable(CharacterTable other) {
        for (int i = 0; i < table.length; i++) {
            table[i] = other.table[i];
        }
    }

    public CharacterTable() { }

    public void map(Character source, Character target) {
        if (isMapped(source)) {
            throw new IllegalArgumentException(source + " is already mapped to " + map(source));
        }

        table[source - 'a'] = target;
    }

    public boolean isMapped(Character source) {
        final int index = source - 'a';
        return table[index] != null;
    }

    public Character map(Character source) {
        if (!isMapped(source)) {
            throw new IllegalArgumentException(source + " is not mapped to any character");
        }
        return table[source - 'a'];
    }
}
