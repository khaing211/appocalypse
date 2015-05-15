package com.github.appocalypse.substitutioncipher;

import java.util.List;

public interface SubstitutionDecrypter {
    /**
     * @param encryptSentence
     * @return a list of all possible decrypt sentence in no particular order.
     *         return an empty list when unable to decrypt.
     */
    List<String> decrypt(String encryptSentence);
}
