package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class AnaglyphDubois {

    public static void main(String[] args) {
        try {
            BufferedImage leftImage = ImageIO.read(new File("left_image.jpg"));
            BufferedImage rightImage = ImageIO.read(new File("right_image.jpg"));

            if (leftImage.getWidth() != rightImage.getWidth() || leftImage.getHeight() != rightImage.getHeight()) {
                throw new IllegalArgumentException("Obrazy muszą mieć takie same wymiary");
            }

            int width = leftImage.getWidth();
            int height = leftImage.getHeight();

            BufferedImage anaglyphImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int leftRGB = leftImage.getRGB(x, y);
                    int Lr = (leftRGB >> 16) & 0xFF;
                    int Lg = (leftRGB >> 8) & 0xFF;
                    int Lb = leftRGB & 0xFF;

                    int rightRGB = rightImage.getRGB(x, y);
                    int Rr = (rightRGB >> 16) & 0xFF;
                    int Rg = (rightRGB >> 8) & 0xFF;
                    int Rb = rightRGB & 0xFF;

                    int Ar = (int) (0.4561 * Lr + 0.500484 * Lg + 0.176381 * Lb - 0.0434706 * Rr - 0.0879388 * Rg - 0.00155529 * Rb);
                    int Ag = (int) (-0.0400822 * Lr - 0.0378246 * Lg - 0.0157589 * Lb + 0.378476 * Rr + 0.73364 * Rg + 0.0184503 * Rb);
                    int Ab = (int) (-0.0152161 * Lr - 0.0205971 * Lg - 0.00546856 * Lb - 0.0721527 * Rr - 0.112961 * Rg + 1.2264 * Rb);

                    Ar = Math.max(0, Math.min(255, Ar));
                    Ag = Math.max(0, Math.min(255, Ag));
                    Ab = Math.max(0, Math.min(255, Ab));

                    int anaglyphRGB = (Ar << 16) | (Ag << 8) | Ab;

                    anaglyphImage.setRGB(x, y, anaglyphRGB);
                }
            }
            ImageIO.write(anaglyphImage, "jpg", new File("anaglyph_image.jpg"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
