package ex03;

import java.util.Scanner;

public class Program {
    final static String STOP = "42";
    final static String WEEK = "Week ";
    static long res = 1000000000000000000L;

    public static void main(String[] args) {
        readInput();
        printResult();
    }

    private static void printResult() {
        for (int weekResult = (int)(res % 10L), weekNum = 1; weekResult != 0 && res != 1L; res /= 10, weekNum++,
                weekResult = (int)(res % 10L)) {
            System.out.print(WEEK);
            System.out.print(weekNum);
            System.out.print(" ");
            for (; weekResult > 0;  weekResult--)
                System.out.print("=");
            System.out.println(">");
        }
    }

    private static void readInput() {
        Scanner sc = new Scanner(System.in);
        String in;
        int weekNum = 1;

        while (sc.hasNextLine()) {
            in = sc.nextLine();
            if (in.equals(STOP))
                break;
            if (in.equals(WEEK + weekNum) && weekNum < 19)
                res += count(sc) * power(weekNum);
            else
                error();
            weekNum++;
        }
    }

    private static long power(int num) {
        long res = 1L;

        while(num > 1) {
            res *= 10L;
            num--;
        }
        return res;
    }

    private static long count(Scanner sc) {
        int res = 10;
        int input;

        for (int counter = 0; counter < 5; counter++) {
            if (!sc.hasNextInt())
                error();
            input = sc.nextInt();
            if (input > 9 || input < 1)
                error();
            if (res > input)
                res = input;
        }
        if (!sc.nextLine().equals(""))
            error();
        return res;
    }

    private static void error() {
        System.err.println("Illegal Argument");
        System.exit( -1);
    }

}
