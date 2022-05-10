package ex03;

import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--threadsCount=")) {
            System.out.println("$ java Program.java --threadsCount=NUMBER");
            System.exit(-1);
        }
        int threadsNumber = 0;
        ArrayList<OwnThread> threads = new ArrayList<>();
        Urls urls = new Urls();
        if (!args[0].substring(15).matches("\\d+"))
            System.exit(-1);
        threadsNumber = Integer.parseInt(args[0].substring(15));
        for (int i = 0; i < threadsNumber; i++) {
            threads.add(new OwnThread(urls, i + 1));
            threads.get(i).start();
        }
        for (OwnThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
