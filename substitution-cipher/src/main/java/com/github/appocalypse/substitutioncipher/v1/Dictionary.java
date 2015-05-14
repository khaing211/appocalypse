package com.github.appocalypse.substitutioncipher.v1;

import com.github.appocalypse.substitutioncipher.CharacterTable;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Dictionary {
    private final TreeSet<String> words;

    public Dictionary(TreeSet<String> words) {
        this.words = words;
    }

    /**
     * @param encryptWord assume to be all lowercase
     * @param table current table
     * @param predicate post-test the new table after decrypt the encryptWord
     */
    public void tryDecrypt(final String encryptWord, final CharacterTable table, final Predicate<CharacterTable> predicate) {
        final NavigableSet<String> wordsWithSameLength = words.subSet(encryptWord, true, encryptWord, true);

        while (!wordsWithSameLength.isEmpty()) {
            // current candidate for the encryptWord
            final String potentialWord = wordsWithSameLength.pollFirst();

            // build a new map table from the old table
            final CharacterTable newTable = new CharacterTable(table);

            // if the current candidate is a good candidate for encrypt
            // new table will be filled and pass into predicate for further
            // test table for other encryptWord
            if (tryDecrypt(encryptWord, potentialWord, newTable)) {
                if (predicate.test(newTable)) {
                    break;
                }
            }
        }
    }

    private boolean tryDecrypt(final String encryptWord, final String potentialWord, final CharacterTable table) {
        for (int i = 0; i < encryptWord.length(); i++) {
            final Character encryptChar = encryptWord.charAt(i);
            final Character decryptChar = potentialWord.charAt(i);
            if (table.isMapped(encryptChar)) {
                if (!table.map(encryptChar).equals(decryptChar)) {
                    return false;
                }
            } else {
                table.map(encryptChar, decryptChar);
            }
        }

        return true;
    }

    public static Dictionary initialize(final Scanner scanner) {
        // ordered the set by length of the word
        final TreeSet<String> words = new TreeSet<String>(Comparator.comparingInt(String::length));
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine().toLowerCase());
        }
        return new Dictionary(words);
    }
}
