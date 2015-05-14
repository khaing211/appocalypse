package com.github.appocalypse.substitutioncipher.v1;

import com.github.appocalypse.substitutioncipher.CharacterTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {
    private final static ArrayList<String> EMPTY_ARRAY_LIST = new  ArrayList<>();
    private final HashMap<Integer, ArrayList<String>> words;

    public Dictionary(HashMap<Integer, ArrayList<String>> words) {
        this.words = words;
    }

    public int getFrequency(int stringLength) {
        return words.getOrDefault(stringLength, EMPTY_ARRAY_LIST).size();
    }

    /**
     * @param encryptWord assume to be all lowercase
     * @param table current table
     * @param decrypter post-test the new table after decrypt the encryptWord
     */
    public void tryDecrypt(final String encryptWord, final CharacterTable table, final SubstitutionCipher.Decrypter decrypter) {
        final ArrayList<String> wordsWithSameLength = words.get(encryptWord.length());

        //System.out.println(wordsWithSameLength);

        for (int i = 0; i < wordsWithSameLength.size(); i++) {
            // current candidate for the encryptWord
            final String potentialWord = wordsWithSameLength.get(i);

            // build a new map table from the old table
            final CharacterTable newTable = new CharacterTable(table);

            // if the current candidate is a good candidate for encrypt
            // new table will be filled and pass into predicate for further
            // test table for other encryptWord
            if (tryDecrypt(encryptWord, potentialWord, newTable)) {
                if (decrypter.test(newTable)) {
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
        // bucket all the words by length
        final HashMap<Integer, ArrayList<String>> words = new HashMap<>();

        while (scanner.hasNextLine()) {
            final String word = scanner.nextLine().toLowerCase();

            words.compute(word.length(), (key, value) -> {
                    if (value == null) {
                        value = new ArrayList<>();
                    }
                    value.add(word);
                    return value;
                });
        }

        return new Dictionary(words);
    }
}
