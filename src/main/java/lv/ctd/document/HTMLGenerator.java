package lv.ctd.document;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lv.ctd.document.exception.HTMLGenerationException;
import lv.ctd.model.PaymentFormData;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

@Component
public class HTMLGenerator implements DocumentGenerator {
     static final String TEMPLATE_LOCATION = "templates/payment_form_template.mustache";
     static final String TEMPORARY_FILE_NAME = "temp.html";
    private Logger LOGGER = Logger.getLogger(HTMLGenerator.class.getName());

    @Override
    public <T extends PaymentFormData> String generateDocument(T data) {
        try {
            File temp = File.createTempFile(TEMPORARY_FILE_NAME, Long.toString(System.nanoTime()));
            try (Writer writer = new FileWriter(temp.getPath())) {
                MustacheFactory mf = new DefaultMustacheFactory();
                Mustache mustache = mf.compile(TEMPLATE_LOCATION);
                mustache.execute(writer, data);
            }
            LOGGER.info("Generated HTML document path: " + temp.getPath());
            return temp.getPath();
        } catch (IOException e) {
            throw new HTMLGenerationException("HTML generation failed");
        }
    }
}
