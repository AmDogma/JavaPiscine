package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Program {

    private static final String SIGNATURES = "/Users/mdewayne/IdeaProjects/day02/src/ex00/signatures.txt";
    private static final String RESULT = "/Users/mdewayne/IdeaProjects/day02/src/ex00/result.txt";
    private static final String END = "42";
    private static final Map<String, String> formats = new HashMap<>();

    public static void main(String[] args) {
        String temp;

        temp = new String(readFile(SIGNATURES, 0));
        parseFormats(temp);
        act();
    }

    private static void act() {
        Scanner sc = new Scanner(System.in);
        String line;
        String readRes;

        line = sc.nextLine();
        writeFile(false, "");
        while (!line.equals(END)) {
            byte[] bytes = readFile(line, 8);
            readRes = transform(bytes);
            readRes = compare(readRes);
            if (readRes.length() > 0)
                writeFile(true, readRes);
            line = sc.nextLine();
        }
        sc.close();
    }

    private static String transform(byte[] bytes) {
        StringBuilder res = new StringBuilder();
        for (byte curr : bytes)
            res.append(String.format("%02X", curr));
        return res.toString();
    }

    private static String compare(String str) {

        if (str.isEmpty()) {
            System.err.println("Empty argument file");
            System.exit(-1);
        }
        for (String key : formats.keySet()) {
            if (str.startsWith(key)) {
                System.out.println("PROCESSED");
                return formats.get(key)  + "\n";
            }
        }
        System.out.println("UNDEFINED");
        return "";
    }

    private static void writeFile(boolean write, String str) {
        try (FileOutputStream fileOut = new FileOutputStream(RESULT, write)){
            if (write)
                fileOut.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseFormats(String str) {
        String[] lines;
        String[] temp;

        if (str.isEmpty()) {
            System.err.println("Empty signatures file");
            System.exit(-1);
        }
        lines = str.split("\n");
        for (String line : lines) {
            temp = line.split(",");
            formats.put(temp[1].replaceAll(" ", ""), temp[0]);
        }
    }

    private static byte[] readFile(String fileName, int bytesNumber) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)){
            if (bytesNumber == 0)
                bytesNumber = fileInputStream.available();
            byte[] bytes = new byte[bytesNumber];
            if (fileInputStream.read(bytes) > 0)
                return bytes;
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

}
