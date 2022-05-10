package ex00.ImagesToChar.src.java.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Convert {

    final char BLACK;
    final char WHITE;
    final String IMAGE_PATH;

    public Convert(char white, char black, String imagePath) {
        IMAGE_PATH = imagePath;
        BLACK = black;
        WHITE = white;
    }

    public void act() {
        try  {
            BufferedImage image = ImageIO.read(new File(IMAGE_PATH));
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                   if(image.getRGB(j, i) == Color.BLACK.getRGB())
                       System.out.print(BLACK);
                   else
                       System.out.print(WHITE);
                }
                System.out.println();
            }
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
