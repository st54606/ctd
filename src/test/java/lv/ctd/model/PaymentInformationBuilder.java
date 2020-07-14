package lv.ctd.model;

public final class PaymentInformationBuilder {
    private String amountAndCurrency;
    private String bankFee;
    private String amountInWords;
    private String paymentType;
    private String exchangeRate;
    private String valueDate;
    private String codeOfExternalPayment;
    private String paymentDetails;

    private PaymentInformationBuilder() {
    }

    public static PaymentInformationBuilder aPaymentInformation() {
        return new PaymentInformationBuilder();
    }

    public PaymentInformationBuilder withAmountAndCurrency(String amountAndCurrency) {
        this.amountAndCurrency = amountAndCurrency;
        return this;
    }

    public PaymentInformationBuilder withBankFee(String bankFee) {
        this.bankFee = bankFee;
        return this;
    }

    public PaymentInformationBuilder withAmountInWords(String amountInWords) {
        this.amountInWords = amountInWords;
        return this;
    }

    public PaymentInformationBuilder withPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public PaymentInformationBuilder withExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
        return this;
    }

    public PaymentInformationBuilder withValueDate(String valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public PaymentInformationBuilder withCodeOfExternalPayment(String codeOfExternalPayment) {
        this.codeOfExternalPayment = codeOfExternalPayment;
        return this;
    }

    public PaymentInformationBuilder withPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
        return this;
    }

    public PaymentInformation build() {
        PaymentInformation paymentInformation = new PaymentInformation();
        paymentInformation.setAmountAndCurrency(amountAndCurrency);
        paymentInformation.setBankFee(bankFee);
        paymentInformation.setAmountInWords(amountInWords);
        paymentInformation.setPaymentType(paymentType);
        paymentInformation.setExchangeRate(exchangeRate);
        paymentInformation.setValueDate(valueDate);
        paymentInformation.setCodeOfExternalPayment(codeOfExternalPayment);
        paymentInformation.setPaymentDetails(paymentDetails);
        return paymentInformation;
    }
}
