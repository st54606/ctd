package lv.ctd.rest;

import lv.ctd.mail.EmailService;
import lv.ctd.model.PaymentFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;


@RestController
public class MailResource {
    @Autowired private EmailService service;

    @RequestMapping(value = "/sendEmail",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Response sendEmail(@RequestBody PaymentFormData paymentFormData) {
        service.send(paymentFormData);
        return Response
                .status(Response.Status.OK)
                .entity(new PaymentFormData())
                .build();
    }
}
