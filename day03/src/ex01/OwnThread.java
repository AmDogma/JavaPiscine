package ex01;

public class OwnThread extends Thread {
    private final int count;
    private final ProducerConsumer producerConsumer;
    private final String name;

    public OwnThread(String name, ProducerConsumer producerConsumer, int count) {
        this.name = name;
        this.producerConsumer = producerConsumer;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            if ("Egg".equals(name))
                producerConsumer.printFirst(i + " " + name);
            else
                producerConsumer.printSecond(i + " " + name);
        }

    }
}
