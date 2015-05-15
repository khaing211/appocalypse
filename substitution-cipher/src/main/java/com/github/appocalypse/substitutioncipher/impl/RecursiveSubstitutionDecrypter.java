package com.github.appocalypse.substitutioncipher.impl;

import com.github.appocalypse.substitutioncipher.*;

import java.util.*;
import java.util.function.Predicate;

public class RecursiveSubstitutionDecrypter implements SubstitutionDecrypter {

    private final Dictionary dictionary;

    public RecursiveSubstitutionDecrypter(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public static RecursiveSubstitutionDecrypter initialize(Scanner scanner) {
        final Dictionary dictionary = Dictionary.initialize(scanner);
        return new RecursiveSubstitutionDecrypter(dictionary);
    }

    @Override
    public List<String> decrypt(String encryptSentence) {
        // convert to lower case
        encryptSentence = encryptSentence.toLowerCase();

        // remove all punctuations and splits by space to words
        final String[] uniqWords = Arrays.stream(encryptSentence.replaceAll("\\p{Punct}", "").split("\\s"))
                .distinct()
                .toArray(it -> new String[it]);

        // there is no words, only punctuations in the sentence
        if (uniqWords.length == 0) return Collections.emptyList();

        // sort in decreasing order of string length
        Arrays.sort(uniqWords, Comparator.comparingInt(String::length).reversed());

        //System.out.println(Arrays.toString(uniqWords));

        final AllPossibleSentenceDecrypter sentenceDecrypter = new AllPossibleSentenceDecrypter(encryptSentence);

        Decrypter decrypter = sentenceDecrypter;

        for (int i = uniqWords.length-1; i > 0; i--) {
            decrypter = new WordDecrypter(uniqWords[i], decrypter);
        }

        final CharacterTable emptyTable = new CharacterTable();

        dictionary.tryDecrypt(uniqWords[0], emptyTable, decrypter);

        return sentenceDecrypter.getDecryptSentences();
    }

    // create an alias
    public static interface Decrypter extends Predicate<CharacterTable> { }

    public class WordDecrypter implements Decrypter {

        private final String encryptWord;
        private final Decrypter nextDecrypter;

        public WordDecrypter(String encryptWord, Decrypter nextDecrypter) {
            this.encryptWord = encryptWord;
            this.nextDecrypter = nextDecrypter;
        }

        @Override
        public boolean test(CharacterTable characterTable) {
            //System.out.println(encryptWord + " " + characterTable);
            dictionary.tryDecrypt(encryptWord, characterTable, nextDecrypter);
            return false;
        }
    }

    public static class AllPossibleSentenceDecrypter implements Decrypter {

        private final String encryptSentence;
        private final List<String> decryptSentences = new LinkedList<>();

        public AllPossibleSentenceDecrypter(String encryptSentence) {
            this.encryptSentence = encryptSentence;
        }

        @Override
        public boolean test(CharacterTable characterTable) {
            decryptSentences.add(decrypt(characterTable));
            return false;
        }

        private String decrypt(CharacterTable characterTable) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < encryptSentence.length(); i++) {
                Character source = encryptSentence.charAt(i);
                if (Character.isLetter(source)) {
                    builder.append(characterTable.map(source));
                } else {
                    builder.append(source);
                }
            }
            return builder.toString();
        }

        public List<String> getDecryptSentences() {
            return decryptSentences;
        }
    }

    public static class Dictionary {
        private final static ArrayList<String> EMPTY_ARRAY_LIST = new  ArrayList<>();
        private final HashMap<Integer, ArrayList<String>> words;

        public Dictionary(HashMap<Integer, ArrayList<String>> words) {
            this.words = words;
        }

        /**
         * @param encryptWord assume to be all lowercase
         * @param table current table
         * @param decrypter post-test the new table after decrypt the encryptWord
         */
        public void tryDecrypt(final String encryptWord, final CharacterTable table, final Decrypter decrypter) {
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
}
