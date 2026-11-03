package com.kapiladithyaa;

public class Base62 {
    private static final String ALPHABET =
        "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long number) {
        if (number == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            long remainder = number % 62;
            sb.append(ALPHABET.charAt((int) remainder));
            number = number / 62;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(encode(0));
        System.out.println(encode(61));
        System.out.println(encode(62));
        System.out.println(encode(125));
    }
}