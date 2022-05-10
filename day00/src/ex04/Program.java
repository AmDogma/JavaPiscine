package ex04;

import java.util.Scanner;

public class Program {
    public final static int MAX_CHARS = 65535;
    public final static int TOP_CHARS = 10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        short charCount[] = countChars(input);
        char top[] = findMax(charCount);
        print(top, charCount);
        sc.close();
    }

    private static void print(char top[], short charCount[]) {
        final int amount = countAmount(top);
        final int max = charCount[top[0]];
        int proc[] = new int[TOP_CHARS];
        double step = (double)max / TOP_CHARS;

        for (int i = 0; i < TOP_CHARS; i++) {
            proc[i] = (int)(charCount[top[i]] / step);
        }
        for (int i = 0; i < TOP_CHARS + 1; i++) {
            for (int j = 0; j < TOP_CHARS; j++) {
                if (charCount[top[j]] > 0) {
                    if (proc[j] == (TOP_CHARS - i))
                        System.out.printf("%d\t", charCount[top[j]]);
                    if (proc[j] > (TOP_CHARS - i))
                        System.out.print("#\t");
                }
            }
            System.out.println();
        }
        for (int i = 0; i < amount; i++)
            System.out.print(top[i] + "\t");
        System.out.println();
    }

    private static int countAmount(char top[]) {
        for (int i = 0; i < top.length; i++) {
            if (top[i] == 0)
                return i;
        }
        return top.length;
    }

    private static short[] countChars(String input) {
        short charCount[] = new short[MAX_CHARS];
        char inputChar[] = input.toCharArray();

        for (int i = 0; input.length() > i; i++) {
            charCount[inputChar[i]]++;
        }
        return charCount;
    }

    private static char[] findMax(short charCount[]) {
        int top[] = new int[TOP_CHARS];
        char let[] = new char[TOP_CHARS];

        for  (int i = 0; i < top.length; i++ ) {
            for (int j = 0; j < charCount.length; j++) {
                if (charCount[j] > top[i] && saved(i, let, (char)j)) {
                    top[i] = charCount[j];
                    let[i] = (char)j;
                }
            }
        }
        return let;
    }

    private static boolean saved(int i, char[] let, char charSaved) {
        for (int g = 0; g < i; g++) {
            if (let[g] == charSaved)
                return false;
        }
        return true;
    }
}
