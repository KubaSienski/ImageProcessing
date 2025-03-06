package org.example;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageSharpening {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        String inputFilePath = "input.jpg";
        Mat src = Imgcodecs.imread(inputFilePath);

        if (src.empty()) {
            System.out.println("Błąd przy wczytywaniu obrazu");
            return;
        }

        Mat blurred = new Mat();
        Imgproc.GaussianBlur(src, blurred, new Size(3, 3), 0);

        Mat mask = new Mat();
        Core.subtract(src, blurred, mask);

        Mat sharpened = new Mat();
        Core.add(src, mask, sharpened);

        String outputFilePath = "output.jpg";
        Imgcodecs.imwrite(outputFilePath, sharpened);

        System.out.println("Obraz został wyostrzony i zapisany do " + outputFilePath);
    }
}
