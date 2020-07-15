package lv.ctd.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lv.ctd.mail.EmailService;
import lv.ctd.model.PaymentDataMockUtils;
import lv.ctd.model.PaymentFormData;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class MailResourceTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private EmailService service;
    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Test
    void sendEmail() {
        PaymentFormData paymentFormData = PaymentDataMockUtils.getPaymentFormData();
        String json = mapper.writeValueAsString(paymentFormData);

        mockMvc.perform(post("/sendEmail")
                .content(json)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).send(any(PaymentFormData.class));
        ArgumentCaptor<PaymentFormData> paymentCaptor = ArgumentCaptor.forClass(PaymentFormData.class);
        verify(service).send(paymentCaptor.capture());

        PaymentFormData capturedPayment = paymentCaptor.getValue();
        assertEquals(paymentFormData.getBeneficiary(), capturedPayment.getBeneficiary());
        assertEquals(paymentFormData.getPaymentInformation(), capturedPayment.getPaymentInformation());
        assertEquals(paymentFormData.getRemitter(), capturedPayment.getRemitter());
        assertEquals(paymentFormData.getCustomerNumber(), capturedPayment.getCustomerNumber());
        assertEquals(paymentFormData.getCurrentDate(), capturedPayment.getCurrentDate());

    }
}