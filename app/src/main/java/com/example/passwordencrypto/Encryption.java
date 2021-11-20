package com.example.passwordencrypto;

public class Encryption {

    private static String encryption(String str, int k) {
        String encryptedStr = "";
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                char lower = (char) (((int) str.charAt(i) + k - 97) % 26 + 97);
                encryptedStr += lower;

            } else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                char upper = (char) (((int) str.charAt(i) +
                        k - 65) % 26 + 65);
                encryptedStr += upper;
            } else if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                char number = (char) (((int) str.charAt(i) +
                        k - 48) % 10 + 48);
                encryptedStr += number;
            } else {
                encryptedStr += str.charAt(i);
            }
        }
        return encryptedStr;
    }
}
