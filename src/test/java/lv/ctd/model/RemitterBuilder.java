package lv.ctd.model;

public final class RemitterBuilder {
    private String name;
    private String code;
    private String accountNumber;

    private RemitterBuilder() {
    }

    public static RemitterBuilder aRemitter() {
        return new RemitterBuilder();
    }

    public RemitterBuilder withRemitterName(String remitterName) {
        this.name = remitterName;
        return this;
    }

    public RemitterBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public RemitterBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public Remitter build() {
        Remitter remitter = new Remitter();
        remitter.setName(name);
        remitter.setCode(code);
        remitter.setAccountNumber(accountNumber);
        return remitter;
    }
}
