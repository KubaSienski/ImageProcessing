package org.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Negative {
    public static void main(String[] args) {
        try {
            // Wczytaj obraz
            File input = new File("img.png");
            BufferedImage image = ImageIO.read(input);

            // Przekształć obraz w negatyw
            BufferedImage negativeImage = createNegative(image);

            // Zapisz obraz negatywny
            File output = new File("output2.png");
            ImageIO.write(negativeImage, "png", output);

            System.out.println("Negatyw obrazu został utworzony.");
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }

    public static BufferedImage createNegative(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage negativeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Przetwarzanie pikseli
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Pobierz wartość piksela
                Color originalColor = new Color(image.getRGB(x, y));

                // Oblicz negatyw koloru piksela
                int red = 255 - originalColor.getRed();
                int green = 255 - originalColor.getGreen();
                int blue = 255 - originalColor.getBlue();

                // Ustaw negatyw koloru piksela na obrazie negatywnym
                Color negativeColor = new Color(red, green, blue);
                negativeImage.setRGB(x, y, negativeColor.getRGB());
            }
        }

        return negativeImage;
    }
}
