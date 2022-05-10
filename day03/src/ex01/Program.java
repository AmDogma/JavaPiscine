package ex01;

import java.util.InputMismatchException;

public class Program {

    public static void main(String[] args) {
        int count = 0;
        ProducerConsumer producerConsumer = new ProducerConsumer();

        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.exit(-1);
        }
        try {
            count = Integer.parseInt(args[0].substring(8));
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        Thread egg = new OwnThread("Egg", producerConsumer, count);
        Thread hen = new OwnThread("Hen", producerConsumer, count);
        egg.start();
        hen.start();

    }
}
