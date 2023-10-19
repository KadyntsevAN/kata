package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(calc(reader.readLine()));
        } catch (Exception e) {
            System.out.println("throw Exeption");
        }
    }

    public static String calc(String input) throws Exception {
        String[] s = input.split(" ");
        String result = "";
        if (s.length != 3) {
            throw new Exception();
        }
        if (validOperation(s[1].charAt(0))) {
            if (romanToIntAndValid(s[0]) != -1 && romanToIntAndValid(s[2]) != -1) {
                result = convertToRoman(account(romanToIntAndValid(s[0]), romanToIntAndValid(s[2]), s[1].charAt(0)));
            } else if (validNumeric(s[0]) && validNumeric(s[2])) {
                result = String.valueOf(account(Integer.parseInt(s[0]), Integer.parseInt(s[2]), s[1].charAt(0)));
            }
        }
        return result;
    }


    public static boolean validNumeric(String s) throws Exception {
        try {
            int a = Integer.parseInt(s);
            if (a < 1 || a > 10) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception();
        }
        return true;
    }

    public static int romanToIntAndValid(String s) throws Exception {
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
        if (num < 1 || num > 10) {
            throw new Exception();
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

    public static String convertToRoman(int number) throws Exception {
        if (number < 1 || number > 100) {
            throw new Exception();
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