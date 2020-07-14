package lv.ctd.mail;

import lv.ctd.document.DocumentGenerator;
import lv.ctd.mail.exception.EMailException;
import lv.ctd.model.PaymentFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {

    @Autowired EmailServiceHelper emailServiceHelper;

    @Qualifier("PDFGenerator")
    @Autowired
    DocumentGenerator documentGenerator;

    public static final String ATTACHED_FILE_NAME = "paper_payment.pdf";

    public void send(PaymentFormData data) {
        JavaMailSenderImpl mailSender = emailServiceHelper.getSender();
        MimeMessage message = getEmailMessageWithPDFAttachment(mailSender, data);
        mailSender.send(message);
    }
    private MimeMessage getEmailMessageWithPDFAttachment(JavaMailSenderImpl mailSender, PaymentFormData data) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            emailServiceHelper.populateEmailMetadata(helper);
            String pdfPath = emailServiceHelper.createAttachment(data, documentGenerator);
            helper.addAttachment(ATTACHED_FILE_NAME, new File(pdfPath));
            return msg;
        } catch (MessagingException e) {
            throw new EMailException("Failed to initialize mail sender");
        }
    }
}
