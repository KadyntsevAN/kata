package com.company;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(calc("1 + 2"));
    }

    public static String calc(String input) throws Exception {
        String[] s = input.split(" ");
        try {
            if (s.length != 3) {
                return "throws Exception";;
            }
            if (validOperation(s[1].charAt(0))) {
                if (romanToIntAndValid(s[0]) != -1 && romanToIntAndValid(s[2]) != -1) {
                    return convertToRoman(account(romanToIntAndValid(s[0]), romanToIntAndValid(s[2]), s[1].charAt(0)));
                } else if (validNumeric(s[0]) && validNumeric(s[2])) {
                    return String.valueOf(account(Integer.parseInt(s[0]), Integer.parseInt(s[2]), s[1].charAt(0)));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("throws Exception");
            System.exit(0);
        }

        return "throws Exception";
    }


    public static boolean validNumeric(String s) {
        try {
            int a = Integer.parseInt(s);
            if (a < 1 || a > 10) {
                throw new IOException();
            }
            return true;
        } catch (NumberFormatException | IOException e) {
            System.out.println("throws Exception");
            System.exit(0);
        }
        return false;
    }

    public static int romanToIntAndValid(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':

                    if (i != s.length() - 1 && s.charAt(i + 1) == 'V') {
                        i++;
                        num += 4;
                    } else if (i != s.length() - 1 && s.charAt(i + 1) == 'X') {
                        i++;
                        num += 9;
                    } else num += 1;
                    break;
                case 'V':
                    num += 5;
                    break;
                case 'X':
                    num += 10;
                    break;
                default:
                    num = -1;
                    return num;
            }
        }
        try {
            if (num < 1 || num > 10) {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("throws Exception");
            System.exit(0);
        }
        return num;
    }


    public static boolean validOperation(char c) {
        return c == '+' || c == '-' || c == '/' || c == '*';
    }

    public static int account(int a, int b, char c) {
        if (c == '+') {
            return a + b;
        } else if (c == '-') {
            return a - b;
        } else if (c == '/') {
            return a / b;
        } else return a * b;
    }

    public static String convertToRoman(int number) {
        if (number < 1 || number > 100) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("throws Exception");
                System.exit(0);
            }
        }

        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] hundreds = {"", "C"};

        int unitDigit = number % 10;
        int tenDigit = (number / 10) % 10;
        int hundredDigit = (number / 100) % 10;

        return hundreds[hundredDigit] + tens[tenDigit] + units[unitDigit];
    }
}