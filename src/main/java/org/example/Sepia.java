package org.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Sepia {
    public static void main(String[] args) {
        try {
            // Wczytaj obraz
            File input = new File("input.png");
            BufferedImage image = ImageIO.read(input);

            // Przekształć obraz na sepię
            BufferedImage sepiaImage = convertToSepia(image, 30); // Przykładowy współczynnik wypełnienia barwą

            // Zapisz obraz w sepii
            File output = new File("output.png");
            ImageIO.write(sepiaImage, "png", output);

            System.out.println("Obraz został przekształcony na sepię.");
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
    }

    public static BufferedImage convertToSepia(BufferedImage image, int fillCoefficient) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage sepiaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Przetwarzanie pikseli
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Pobierz kolor piksela obrazu w odcieniach szarości
                Color grayscaleColor = new Color(image.getRGB(x, y));

                // Wyodrębnij składowe koloru
                int gray = grayscaleColor.getRed();
                int red = Math.min(255, gray + 2 * fillCoefficient);
                int green = Math.min(255, gray + fillCoefficient);

                // Ustaw nowy kolor na obrazie w sepii
                Color sepiaColor = new Color(red, green, gray); // Niebieska składowa pozostaje bez zmian
                sepiaImage.setRGB(x, y, sepiaColor.getRGB());
            }
        }

        return sepiaImage;
    }
}
