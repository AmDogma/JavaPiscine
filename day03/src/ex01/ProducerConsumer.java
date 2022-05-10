package ex01;

public class ProducerConsumer extends Thread {
    private static boolean bool = false;

    public synchronized void printFirst(String name) {
        if (bool) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name);
        bool = true;
        notify();
    }

    public synchronized void printSecond(String name) {
        if (!bool) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name);
        bool = false;
        notify();
    }

}
