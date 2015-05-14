package com.github.appocalypse.substitutioncipher.v1;

import java.util.Scanner;

public class SubstitutionCipher {

    private final Dictionary dictionary;

    public SubstitutionCipher(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public static SubstitutionCipher initialize(Scanner scanner) {
        final Dictionary dictionary = Dictionary.initialize(scanner);
        return new SubstitutionCipher(dictionary);
    }

    public String decrypt(String sentence) {
        // TODO:
        return sentence;
    }
}
