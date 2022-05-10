package ex02.ImagesToChar.src.java.edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import ex02.ImagesToChar.src.java.edu.school21.printer.logic.Convert;


@Parameters(separators = "=")
public class Program {

    @Parameter(names = "--black")
    private String black;
    @Parameter(names = "--white")
    private String white;

    public static void main(String[] args) {
        if (args.length != 2)
            System.exit(-1);
        Program program = new Program();
        try {
            JCommander.newBuilder()
                    .addObject(program)
                    .build()
                    .parse(args);
            program.run();
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
        }
    }
    public void run() {
        if (black == null || white == null)
            System.exit(-1);
        Convert convert = new Convert();
        convert.act(white, black);
    }
}
