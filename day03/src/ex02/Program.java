package ex02;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        int arraySize = Integer.parseInt(args[0].substring(12));
        int threadsCount = Integer.parseInt(args[1].substring(15));
        List<Integer> numbers = new ArrayList<>(arraySize);
        int range = (arraySize + ((arraySize + 1)%threadsCount) / 2) / threadsCount;
        List<Thread> threadsList = new ArrayList<>(threadsCount);
        int beginInd = 0;
        int lastInd = 0;

        for (int i = 0; i < arraySize; i++) {
            numbers.add((int)(Math.random() * 3) + 1);
        }
        System.out.println("Sum: " + sum(numbers));
        for (int i = 0; i < threadsCount; i++) {
            beginInd = i * range;
            lastInd = beginInd + range;
            if (i == threadsCount - 1)
                lastInd = arraySize;
            threadsList.add(new OwnThreads(numbers, beginInd, lastInd, i + 1));
        }
        for (Thread thread : threadsList) {
            thread.start();
        }
        for (Thread thread : threadsList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum by threads: " + OwnThreads.getSum());
    }

    private static int sum(List<Integer> list) {
        int tmp = 0;
        for (int i : list)
            tmp += i;
        return tmp;
    }
}