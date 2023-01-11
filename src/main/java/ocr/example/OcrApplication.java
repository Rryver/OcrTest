package ocr.example;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class OcrApplication {
    public static void main(String[] args) throws FileNotFoundException {
        File imageFile = new File("test_1.1.jpg");
        if (!imageFile.exists()) {
            throw new FileNotFoundException();
        }
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        instance.setLanguage("rus");
        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
        instance.setDatapath("tessdata"); // path to tessdata directory

        try {
            Rectangle rectangle = new Rectangle();
            rectangle.setBounds(142, 20, 580, 50);
            String result = instance.doOCR(imageFile, rectangle);

            Rectangle rectangle2 = new Rectangle();
            rectangle2.setBounds(40, 138, 85, 25);
            String result2 = instance.doOCR(imageFile, rectangle2);

            PrintWriter pw = new PrintWriter("output.txt");

            pw.print(result);
            pw.print(result2);

            pw.close();
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
