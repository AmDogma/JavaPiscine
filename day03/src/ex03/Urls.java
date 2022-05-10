package ex03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Urls {
    final private ArrayList<String> tasks = new ArrayList<>();
    final static private String FILENAME = "/Users/mdewayne/IdeaProjects/day03/src/ex03/files_urls.txt"; // change to folder path
    public Urls() {
        try (Scanner sc = new Scanner(new File(FILENAME))) {
            String line;
            while (sc.hasNext()) {
                line = sc.nextLine().trim();
                System.out.println(line);
                if (!line.isEmpty()) {
                    tasks.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized String get() {
        if (tasks.size() > 1) {
            String task = tasks.get(0);
            tasks.remove(0);
            return task;
        }
        return "";
    }

}
