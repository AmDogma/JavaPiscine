package ex00;

import java.util.InputMismatchException;

public class Program {
    public static void main(String[] args) {
        int count = 0;

        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.exit(-1);
        }
        try {
             count= Integer.parseInt(args[0].substring(8));
        } catch (InputMismatchException e) {
            System.out.println(e);
            System.exit(-1);
        }
        OwnThread egg = new OwnThread("Egg", count);
        OwnThread hen = new OwnThread("Hen", count);
        for (int i = 0 ; i < 10000; i++);
        egg.start();
        hen.start();
        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < count; i++)
            System.out.println("Human");
    }
}
