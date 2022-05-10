package ex00;

public class OwnThread extends Thread {
    private final int count;
    private final String name;

    public OwnThread(String name, int count) {
        this.count = count;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(name);
        }
    }
}
