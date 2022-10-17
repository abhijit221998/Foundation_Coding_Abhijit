package foundation.assignment;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        System.out.println("Enter Your String= ");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine(); //here we take ip from user
        System.out.println(palindromeString(str));

    }

    static boolean palindromeString(String substr) {
        String a = ""; //1
        int length = substr.length();

        for (int i = length - 1; i >= 0; i--) {
            a = a + substr.charAt(i);
        }
        if (substr.equals(a)) {
            return true;
        } else {
            return false;
        }
    }
}

