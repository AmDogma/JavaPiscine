package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long res = 0L;
        long num;

        while(sc.hasNextLong()) {
            num = sc.nextLong();
            if (num == 42L)
                break;

            res += prime(count(num));
        }
        System.out.println("Count of coffee-request – " + res);
        sc.close();
    }

    private static long count(long num) {
        long res = 0L;

        while (num != 0L) {
            res += num % 10L;
            num /= 10L;
        }
        return res;
    }

    private static long prime(long num) {
        for(long i = 2; i * i <= num; i++) {
            if (num % i == 0L)
                return 0L;
        }
        return 1L;
    }

}
