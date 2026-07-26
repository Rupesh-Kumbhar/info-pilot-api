package com.practice.info_pilot_api.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class PdfUtil {

    public static String extractText(String filePath) throws IOException {

        File pdfFile =new File(filePath);

        try (PDDocument document =Loader.loadPDF(pdfFile)) {

            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
}
