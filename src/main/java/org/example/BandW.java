package org.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BandW {
    public static void main(String[] args) {
        try {
            // Wczytaj obraz
            File input = new File("input.png");
            BufferedImage image = ImageIO.read(input);

            // Przekształć obraz na odcienie szarości
            BufferedImage grayscaleImage = convertToGrayscale(image);

            // Zapisz obraz w odcieniach szarości
            File output = new File("output.png");
            ImageIO.write(grayscaleImage, "png", output);

            System.out.println("Obraz został przekształcony na odcienie szarości.");
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }

    public static BufferedImage convertToGrayscale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Przetwarzanie pikseli
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Pobierz kolor piksela
                Color originalColor = new Color(image.getRGB(x, y));

                // Oblicz jasność (odcienie szarości)
                int gray = (originalColor.getRed() + originalColor.getGreen() + originalColor.getBlue()) / 3;

                // Ustaw nowy kolor na obrazie w odcieniach szarości
                Color grayscaleColor = new Color(gray, gray, gray);
                grayscaleImage.setRGB(x, y, grayscaleColor.getRGB());
            }
        }

        return grayscaleImage;
    }
}
