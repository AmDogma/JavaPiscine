package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int num = sc.nextInt();
        int operations = 1;

        if (num < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        for(int i = 2; i * i <= num; i++, operations++) {
            if (num % i == 0)
                print(operations, false);
        }
        print(operations, true);
    }

    private static void print(int operations, boolean res) {
        System.out.println(res + " " + operations);
        System.exit(0);
    }
}
