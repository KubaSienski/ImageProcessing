package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedImage image = ImageIO.read(new File("input.jpg"));

            int width = image.getWidth();
            int height = image.getHeight();
            int newWidth = 2048;
            int newHeight = 2048;

            double rx = (double) newWidth / width;
            double ry = (double) newHeight / height;

            // setting up a new img
            BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            // Iteration over new img
            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < newWidth; x++) {
                    double sourceX = x / rx;
                    double sourceY = y / ry;

                    int x1 = (int) Math.floor(sourceX);
                    int y1 = (int) Math.floor(sourceY);
                    int x2 = Math.min(x1 + 1, width - 1);
                    int y2 = Math.min(y1 + 1, height - 1);

                    double a = sourceX - x1;
                    double b = sourceY - y1;

                    // linear interpolation horizontally
                    int f0 = interpolate(image.getRGB(x1, y1), image.getRGB(x2, y1), a);
                    int f1 = interpolate(image.getRGB(x1, y2), image.getRGB(x2, y2), a);

                    // linear interpolation vertically
                    int interpolatedColor = interpolate(f0, f1, b);

                    outputImage.setRGB(x, y, interpolatedColor);
                }
            }

            // Saving new file
            File outputFile = new File("output.jpg");
            ImageIO.write(outputImage, "jpg", outputFile);
            System.out.println("Interpolacja zakończona. Zapisano obraz do pliku output.jpg.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // interpolation
    private static int interpolate(int c0, int c1, double alpha) {
        int red = (int) ((1 - alpha) * ((c0 >> 16) & 0xFF) + alpha * ((c1 >> 16) & 0xFF));
        int green = (int) ((1 - alpha) * ((c0 >> 8) & 0xFF) + alpha * ((c1 >> 8) & 0xFF));
        int blue = (int) ((1 - alpha) * (c0 & 0xFF) + alpha * (c1 & 0xFF));
        return (red << 16) | (green << 8) | blue;
    }
}
