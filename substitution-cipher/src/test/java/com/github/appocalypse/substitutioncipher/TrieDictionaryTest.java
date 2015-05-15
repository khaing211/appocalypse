package com.github.appocalypse.substitutioncipher;

import org.junit.Test;

import java.util.Scanner;

public class TrieDictionaryTest {
    @Test
    public void test() {
        TrieDictionary dictionary = TrieDictionary.initialize(new Scanner(ClassLoader.getSystemResourceAsStream("wordsEn.txt")));
        dictionary.printOut();
    }
}
