package lv.ctd.document;

import lv.ctd.model.PaymentFormData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.quality.Strictness;

import static lv.ctd.model.PaymentDataMockUtils.getPaymentFormData;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
class PDFGeneratorTest {

    @InjectMocks DocumentGenerator pdfGenerator = new PDFGenerator();
    @Mock private DocumentGenerator htmlGenerator;

    private MockitoSession mockitoSession;

    @AfterEach
    void tearDown() {
        mockitoSession.finishMocking();
    }

    @BeforeEach
    void setUp() {
        mockitoSession = Mockito.mockitoSession()
                .initMocks(this)
                .strictness(Strictness.STRICT_STUBS)
                .startMocking();
    }

    @Test
    public void test_generatePdf() {
        PaymentFormData paymentFormData = getPaymentFormData();
        String htmlPath = "src/test/resources/temp_files/html";
        doReturn(htmlPath).when(htmlGenerator).generateDocument(any());

        String pdfPath = pdfGenerator.generateDocument(paymentFormData);

        assertNotNull(pdfPath);
        assertTrue(pdfPath.contains(PDFGenerator.TEMPORARY_FILE_NAME));
    }
}