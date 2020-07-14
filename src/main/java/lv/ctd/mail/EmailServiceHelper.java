package lv.ctd.mail;

import lv.ctd.document.DocumentGenerator;
import lv.ctd.model.PaymentFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Properties;


@Component
public class EmailServiceHelper {
    @Autowired private EmailConfiguration emailConfiguration;

     String createAttachment(PaymentFormData data, DocumentGenerator documentGenerator) {
        return documentGenerator.generateDocument(data);
    }

    JavaMailSenderImpl getSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfiguration.getHost());
        mailSender.setPort(emailConfiguration.getPort());
        mailSender.setUsername(emailConfiguration.getUserName());
        mailSender.setPassword(emailConfiguration.getPassword());
        iniSenderProperties(mailSender);
        return mailSender;
    }

    void iniSenderProperties(JavaMailSenderImpl mailSender) {
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }
    void populateEmailMetadata(MimeMessageHelper helper) throws MessagingException {
        helper.setFrom("info@citadele.lv");
        helper.setTo("v.lovcevics@gmail.com");
        helper.setSubject("New payment information");
        helper.setText("Please check the payment information in the attachment.");
    }


}
