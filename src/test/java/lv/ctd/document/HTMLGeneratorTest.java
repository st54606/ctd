package lv.ctd.document;

import lv.ctd.model.PaymentDataMockUtils;
import lv.ctd.model.PaymentFormData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class HTMLGeneratorTest {

    @InjectMocks DocumentGenerator documentGenerator = new HTMLGenerator();

    @Test
    void test_generateHtmlWithAttributes_shouldGenerateHtmlFile() {
        String htmlPath = documentGenerator.generateDocument(PaymentDataMockUtils.getPaymentFormData());

        assertNotNull(htmlPath);
        assertTrue(htmlPath.contains(HTMLGenerator.TEMPORARY_FILE_NAME));
    }
}