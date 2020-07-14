package lv.ctd.mail;

import lombok.SneakyThrows;
import lv.ctd.document.DocumentGenerator;
import lv.ctd.model.PaymentDataMockUtils;
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
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
class EmailServiceHelperTest {

    @InjectMocks EmailServiceHelper emailServiceHelper = new EmailServiceHelper();
    @Mock EmailConfiguration emailConfiguration;
    @Mock DocumentGenerator documentGenerator;
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
    void test_createAttachment() {
        PaymentFormData paymentFormData = PaymentDataMockUtils.getPaymentFormData();
        doReturn("PathToDocument").when(documentGenerator).generateDocument(paymentFormData);

        String attachment = emailServiceHelper.createAttachment(paymentFormData, documentGenerator);

        assertEquals(attachment, "PathToDocument");
    }

    @Test
    void test_getSender() {
        doReturn("smtp.mailtrap.io").when(emailConfiguration).getHost();
        doReturn(587).when(emailConfiguration).getPort();
        doReturn("87ba3d9555fae8").when(emailConfiguration).getUserName();
        doReturn("91cb4379af43ed").when(emailConfiguration).getPassword();

        JavaMailSenderImpl sender = emailServiceHelper.getSender();


        assertEquals(sender.getHost(), "smtp.mailtrap.io");
        assertEquals(sender.getPort(), 587);
        assertEquals(sender.getPassword(), "91cb4379af43ed");
        assertEquals(sender.getUsername(), "87ba3d9555fae8");
        assertEquals(sender.getJavaMailProperties().get("mail.smtp.starttls.enable"), "true");
        assertEquals(sender.getJavaMailProperties().get("mail.debug"), "true");
        assertEquals(sender.getJavaMailProperties().get("mail.transport.protocol"), "smtp");
        assertEquals(sender.getJavaMailProperties().get("mail.smtp.starttls.enable"), "true");
        assertEquals(sender.getJavaMailProperties().get("mail.smtp.auth"), "true");
    }

    @Test
    void test_initEmailSenderProperties() {
        JavaMailSenderImpl sender = emailServiceHelper.getSender();

        emailServiceHelper.iniSenderProperties(sender);

        assertEquals(sender.getJavaMailProperties().get("mail.debug"), "true");
        assertEquals(sender.getJavaMailProperties().get("mail.transport.protocol"), "smtp");
        assertEquals(sender.getJavaMailProperties().get("mail.smtp.starttls.enable"), "true");
        assertEquals(sender.getJavaMailProperties().get("mail.smtp.auth"), "true");

    }

    @SneakyThrows
    @Test
    void test_populateEmailMetadata() {
        JavaMailSenderImpl sender = emailServiceHelper.getSender();
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        emailServiceHelper.populateEmailMetadata(helper);

        assertEquals(helper.getMimeMessage().getFrom()[0].toString(), "info@citadele.lv");
        assertEquals(helper.getMimeMessage().getRecipients(Message.RecipientType.TO)[0].toString(), "v.lovcevics@gmail.com");
        assertEquals(helper.getMimeMessage().getSubject(), "New payment information");
        assertEquals(helper.getMimeMessage().getContent(), "Please check the payment information in the attachment.");

    }
}