package lv.ctd.model;

import static lv.ctd.model.BeneficiaryBuilder.aBeneficiary;
import static lv.ctd.model.PaymentFormDataBuilder.aPaymentFormData;
import static lv.ctd.model.PaymentInformationBuilder.aPaymentInformation;
import static lv.ctd.model.RemitterBuilder.aRemitter;

public class PaymentDataMockUtils {

    public static PaymentFormData getPaymentFormData() {
        Remitter remitter = aRemitter()
                .withRemitterName("My name")
                .withAccountNumber("LVUNLA50880808080880")
                .withCode("LVUNLA22")
                .build();

        PaymentInformation paymentInformation = aPaymentInformation()
                .withCodeOfExternalPayment("CodeOfExternalPayment")
                .withPaymentDetails("Payment details")
                .withPaymentType("Annual payment")
                .withAmountAndCurrency("2000000LVL")
                .withAmountInWords("To much")
                .withBankFee("20.00")
                .withExchangeRate("1,1")
                .withValueDate("20.20.2020")
                .build();

        Beneficiary beneficiary = aBeneficiary()
                .withBeneficiaryName("BeneficiaryName")
                .withAccountNumber("accountNumber20202020220")
                .withBankCode("NonameCode")
                .withBankName("Noname")
                .withRegistrationNumber("LV40223368877")
                .withResidenceCountry("Latvia")
                .build();

        return aPaymentFormData()
                .withCurrentDate("27.07.2020")
                .withCustomerNumber("0123456789")
                .withPaymentInformation(paymentInformation)
                .withBeneficiary(beneficiary)
                .withRemitter(remitter)
                .build();
    }
}
