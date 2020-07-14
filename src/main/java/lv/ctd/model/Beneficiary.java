package lv.ctd.model;

import lombok.Data;

@Data
public class Beneficiary {
    private String name;
    private String registrationNumber;
    private String accountNumber;
    private String residenceCountry;
    private String bankName;
    private String bankCode;
}