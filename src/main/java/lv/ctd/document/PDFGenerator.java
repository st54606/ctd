package lv.ctd.document;

import com.itextpdf.text.DocumentException;
import lv.ctd.document.exception.PDFGenerationException;
import lv.ctd.model.PaymentFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

@Component
public class PDFGenerator implements DocumentGenerator {
    public static final String TEMPORARY_FILE_NAME = "temp.pdf";
    private Logger LOGGER = Logger.getLogger(PDFGenerator.class.getName());

    @Qualifier("HTMLGenerator")
    @Autowired DocumentGenerator hGenerator;

    @Override
    public <T extends PaymentFormData> String generateDocument(T data) {
        try {
            String inputHtmlPath = hGenerator.generateDocument(data);
            String url = new File(inputHtmlPath).toURI().toURL().toString();
            File temp = File.createTempFile(TEMPORARY_FILE_NAME, Long.toString(System.nanoTime()));

            OutputStream out = new FileOutputStream(temp.getPath());

            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocument(url);
            renderer.layout();
            renderer.createPDF(out);

            out.close();
            LOGGER.info("PDF path: " + temp.getPath());
            return temp.getPath();
        } catch (DocumentException | IOException e) {
            throw new PDFGenerationException("PDF generation failed");
        }
    }
}
