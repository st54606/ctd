package lv.ctd.model;

import lombok.Data;

@Data
public class PaymentInformation {
    private String amountAndCurrency;
    private String bankFee;
    private String amountInWords;
    private String paymentType;
    private String exchangeRate;
    private String valueDate;
    private String codeOfExternalPayment;
    private String paymentDetails;
}
