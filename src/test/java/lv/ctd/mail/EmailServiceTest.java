package lv.ctd.mail;

import lv.ctd.model.PaymentDataMockUtils;
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
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
class EmailServiceTest{


    @InjectMocks EmailService service = new EmailService();

    @Mock EmailServiceHelper emailServiceHelper;
    @Mock JavaMailSenderImpl mailSender;
    @Mock MimeMessage mimeMessage;
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
    void verify_flow_send() {
        doReturn(mailSender).when(emailServiceHelper).getSender();
        doReturn("Path").when(emailServiceHelper).createAttachment(any(), any());
        doNothing().when(mailSender).send(any(MimeMessage.class));
        doReturn(mimeMessage).when(mailSender).createMimeMessage();

        service.send(PaymentDataMockUtils.getPaymentFormData());

        verify(mailSender).send(any(MimeMessage.class));
        verifyNoMoreInteractions(mailSender);
    }
}
