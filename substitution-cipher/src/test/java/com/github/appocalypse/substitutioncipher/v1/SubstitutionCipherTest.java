package com.github.appocalypse.substitutioncipher.v1;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class SubstitutionCipherTest {

    private static SubstitutionCipher cipher;

    @BeforeClass
    public static void before() {
        cipher = SubstitutionCipher.initialize(new Scanner(ClassLoader.getSystemResourceAsStream("wordsEn.txt")));
    }

    @Test
    public void testFleeAtOnceWeAreDiscovered() {
        decrypt("SIAA ZQ LKBA. VA ZOA RFPBLUAOAR!");
    }

    @Test
    public void testDefendTheEastWallOfTheCastle() {
        decrypt("giuifg cei iprc tpnn du cei qprcni");
    }

    private void decrypt(String encryptSentence) {
        List<String> result = cipher.decrypt(encryptSentence);
        result.stream().forEach(System.out::println);
        System.out.println("======");
    }
}
