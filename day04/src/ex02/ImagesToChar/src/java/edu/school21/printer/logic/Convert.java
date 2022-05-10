package ex02.ImagesToChar.src.java.edu.school21.printer.logic;

import com.diogonunes.jcolor.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Convert {

    final static String IMAGE_PATH = "/Users/mdewayne/IdeaProjects/day04/src/ex02/ImagesToChar/src/resources/it.bmp"; //change
    private static final Map<String, Attribute> colors = new HashMap<>();
    static {
        colors.put("BLACK", Attribute.BLACK_BACK());
        colors.put("WHITE", Attribute.WHITE_BACK());
        colors.put("RED", Attribute.RED_BACK());
        colors.put("GREEN", Attribute.GREEN_BACK());
        colors.put("BLUE", Attribute.BLUE_BACK());
        colors.put("YELLOW", Attribute.YELLOW_BACK());
        colors.put("CYAN", Attribute.CYAN_BACK());
    }

    public void act(String w, String b) {
        Attribute white = colors.get(w);
        Attribute black = colors.get(b);
        if (white == null || black == null) {
            System.out.println("Values can be only \"BLACK\" " +
                    "\"WHITE\" \"RED\" \"GREEN\" \"BLUE\" \"YELLOW\" \"CYAN\"");
            System.exit(-1);
        }
        try  {
            BufferedImage image = ImageIO.read(new File(new File(IMAGE_PATH).getAbsolutePath())); // rename
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                   if(image.getRGB(j, i) == Color.BLACK.getRGB())
                       System.out.print(Ansi.colorize(" ", black));
                   else
                       System.out.print(Ansi.colorize(" ", white));
                }
                System.out.println();
            }
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
