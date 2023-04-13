package com.iscas.common.aspose.tools;

import com.aspose.cad.Color;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PdfOptions;

import java.io.InputStream;
import java.io.OutputStream;

public class CadUtils {
    private CadUtils() {}

    public static void cadToPdf(InputStream is, OutputStream os)  {
        try (com.aspose.cad.Image cadImage = com.aspose.cad.Image.load(is)) {
            CadRasterizationOptions cadRasterizationOptions = new CadRasterizationOptions();
            cadRasterizationOptions.setLayouts(new String[]{"Model"});
            cadRasterizationOptions.setNoScaling(true);
            cadRasterizationOptions.setBackgroundColor(Color.getWhite());
            cadRasterizationOptions.setPageWidth(cadImage.getWidth());
            cadRasterizationOptions.setPageHeight(cadImage.getHeight());
            cadRasterizationOptions.setPdfProductLocation("center");
            cadRasterizationOptions.setAutomaticLayoutsScaling(true);
            cadRasterizationOptions.setDrawType(CadDrawTypeMode.UseObjectColor);
            PdfOptions pdfOptions = new PdfOptions();
            pdfOptions.setVectorRasterizationOptions(cadRasterizationOptions);
            cadImage.save(os, pdfOptions);
        }
    }



//    public static void main(String[] args) throws IOException {
//        InputStream is = Files.newInputStream(Paths.get(new File("D:\\ideaProjects\\file-preview\\server\\src\\main\\resources\\static\\商场设计全套图.dwg").toURI()));
//        try (OutputStream os = Files.newOutputStream(Paths.get(new File("d:/tmp/test.pdf").toURI()))) {
//            CadUtils.cadToPdf(is, os);
//        }
//    }
}
