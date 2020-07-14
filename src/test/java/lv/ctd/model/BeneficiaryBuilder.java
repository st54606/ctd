package lv.ctd.model;

public final class BeneficiaryBuilder {
    private String beneficiaryName;
    private String registrationNumber;
    private String accountNumber;
    private String residenceCountry;
    private String bankName;
    private String bankCode;

    private BeneficiaryBuilder() {
    }

    public static BeneficiaryBuilder aBeneficiary() {
        return new BeneficiaryBuilder();
    }

    public BeneficiaryBuilder withBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
        return this;
    }

    public BeneficiaryBuilder withRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public BeneficiaryBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BeneficiaryBuilder withResidenceCountry(String residenceCountry) {
        this.residenceCountry = residenceCountry;
        return this;
    }

    public BeneficiaryBuilder withBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public BeneficiaryBuilder withBankCode(String bankCode) {
        this.bankCode = bankCode;
        return this;
    }

    public Beneficiary build() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName(beneficiaryName);
        beneficiary.setRegistrationNumber(registrationNumber);
        beneficiary.setAccountNumber(accountNumber);
        beneficiary.setResidenceCountry(residenceCountry);
        beneficiary.setBankName(bankName);
        beneficiary.setBankCode(bankCode);
        return beneficiary;
    }
}
