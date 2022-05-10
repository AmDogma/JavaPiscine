package ex00.ImagesToChar.src.java.edu.school21.printer.app; // rename

import ex00.ImagesToChar.src.java.edu.school21.printer.logic.Convert; // rename

public class Program {
    public static void main(String[] args) {
        if (args.length != 3 || !args[0].startsWith("--white=")
                || !args[1].startsWith("--black=")) {
            System.exit(-1);
        }
        if (args[0].length() != 9 || args[1].length() != 9)
            System.exit(-1);
        char white = args[0].charAt(8);
        char black = args[1].charAt(8);
        System.out.println(args[2] + " " + white + " " + black); // delete

        Convert convert = new Convert(white, black, args[2]);
        convert.act();
    }
}
