package lv.ctd.model;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentFormData {
    private String customerNumber;
    private String currentDate;
    private Remitter remitter;
    private Beneficiary beneficiary;
    private PaymentInformation paymentInformation;
}
