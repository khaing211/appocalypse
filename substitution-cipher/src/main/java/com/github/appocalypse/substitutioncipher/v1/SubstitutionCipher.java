package com.github.appocalypse.substitutioncipher.v1;

import com.github.appocalypse.substitutioncipher.CharacterTable;

import java.util.*;
import java.util.function.Predicate;

public class SubstitutionCipher {

    private final Dictionary dictionary;

    public SubstitutionCipher(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public static SubstitutionCipher initialize(Scanner scanner) {
        final Dictionary dictionary = Dictionary.initialize(scanner);
        return new SubstitutionCipher(dictionary);
    }

    public List<String> decrypt(String encryptSentence) {
        // convert to lower case
        encryptSentence = encryptSentence.toLowerCase();

        // remove all punctuations and splits by space to words
        final String[] uniqWords = Arrays.stream(encryptSentence.replaceAll("\\p{Punct}", "").split("\\s"))
                .distinct()
                .toArray(it -> new String[it]);

        // there is no words, only punctuations in the sentence
        if (uniqWords.length == 0) return Arrays.asList(encryptSentence);

        // sort in decreasing order number of words of same length
        //Arrays.sort(uniqWords, Comparator.comparingInt((String it) -> dictionary.getFrequency(it.length())).reversed());

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
}
