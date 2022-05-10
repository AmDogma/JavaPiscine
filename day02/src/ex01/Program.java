package ex01;

import java.io.*;
import java.util.*;

public class Program {
    private static final Map<String, Integer> list1 = new HashMap<>();
    private static final Map<String, Integer> list2 = new HashMap<>();
    private static final String DICTIONARY = "dictionary.txt";

    public static void main(String[] args) {
        if (args.length != 2)
            System.exit(-1);
        readAndFillList(args[0], list1);
        readAndFillList(args[1], list2);
        countSimilar();
        makeDictionary();
    }

    public static void makeDictionary() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(DICTIONARY))) {
            for (String key : list1.keySet())
                bufferedWriter.write(key + ": " + list1.get(key) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void countSimilar() {
        Set<String> allList = new HashSet<>(list1.keySet());
        int num1 = 0, a = 0, b = 0;
        String res;

        allList.addAll(list2.keySet());
        for (String key : allList) {
            num1 += list1.getOrDefault(key, 0) * list2.getOrDefault(key, 0);
            a += Math.pow(list1.getOrDefault(key, 0), 2);
            b += Math.pow(list2.getOrDefault(key, 0), 2);
        }
        for (String key : allList) {
            list1.put(key, list2.getOrDefault(key, 0) + list1.getOrDefault(key, 0));
        }
        res = String.format("%.3f", num1 / (Math.sqrt(a) * Math.sqrt(b)));
        if (res.equals("NaN"))
            res = "0";
        else
            res = res.substring(0, res.length() - 1);
        System.out.println("Similarity = " + res);
    }

    private static void readAndFillList(String fileName, Map<String, Integer> list) {
        String[] temp;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                temp = bufferedReader.readLine().split(" ");
                for (String i : temp) {
                    if (i.isEmpty())
                        continue;
                    list.put(i, list.getOrDefault(i, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
