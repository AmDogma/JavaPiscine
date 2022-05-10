package ex02;

import java.util.List;

public class OwnThreads extends Thread {

    private final String result;
    private static int sum = 0;

    public OwnThreads(List<Integer> numbers, int start, int end, int id) {
        int res = 0;

        for (int i = start;  i < end; i++) {
            res += numbers.get(i);
        }
        end--;
        summarize(res);
        result = String.format("Thread %d: from %d to %d sum is %d", id, start, end, res);
    }

    private synchronized void summarize(int res) {
        sum += res;
    }

    public static int getSum() {
        return sum;
    }

    @Override
    public void run() {
        System.out.println(result);
    }
}