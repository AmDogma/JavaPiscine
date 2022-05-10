package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Scanner;

public class Program {
    private static String path = "";
    private static File file;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        String[] arg;

        if (args.length != 1 || !args[0].startsWith("--current-folder=") || args[0].length() < 18)
            System.exit(-1);
        path = args[0].substring(17);
        cd(path);
        while(sc.hasNext()) {
            command = sc.nextLine();
            arg = command.split(" ");
            if (command.equals("ls"))
                ls();
            else if (command.startsWith("mv") && arg.length == 3)
                    mv(arg[1], arg[2]);
            else if (command.startsWith("cd") && arg.length == 2)
                    cd(arg[1]);
            else if (command.equals("exit"))
                break;
        }
    }

    private static void ls() {
        for (File f : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("%s %dKB\n", f.getName(), getSize(f) / 1024);
        }
    }

    private static long getSize(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        long size = 0;
        for (File f : Objects.requireNonNull(file.listFiles())) {
            size += getSize(f);
        }
        return size;
    }

    private static void mv(String from, String to) {
        File fromFile;
        File toFile;
        if (from.startsWith("/"))
            fromFile = new File(from);
        else
            fromFile = new File(path + "/" + from);
        if (to.startsWith("/"))
            toFile = new File(to);
        else
            toFile = new File(path + "/" + to);
        try {
            if (toFile.exists() && toFile.isDirectory())
                toFile = new File(toFile.getAbsolutePath() + "/" + fromFile.getName());
            Files.move(fromFile.toPath().normalize(), toFile.toPath().normalize());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void cd(String newPath) {
        File newFile;

        if (!newPath.startsWith("/"))
            newPath = path + "/" + newPath;
        newFile = new File(newPath);
        if (newFile.exists() && newFile.isDirectory()) {
            path = newPath;
            file = newFile;
            System.out.println(file.toPath().toAbsolutePath().normalize());
        }
        else {
            System.err.println("No such directory");
        }
    }

}
