import java.time.LocalDate;

public class Customer {

    protected long ssn;
    protected String surName;
    protected String lastName;
    protected LocalDate date;
    protected boolean payingCustomer;

    public Customer(long ssn, String surName, String lastName, LocalDate date, boolean payingCustomer) {
        this.ssn = ssn;
        this.surName = surName;
        this.lastName = lastName;
        this.date = date;
        this.payingCustomer = payingCustomer;
    }

    public String printPayingCustomer(boolean payingCustomer){
        String paying;
        if(payingCustomer){
            paying = " Ja";
        }
        else{
            paying = " Nej";
        }
        return paying;
    }

    @Override
    public String toString() {
        return "Personnummer: " + ssn +
                "\nFörnamn: " + surName +
                "\nEfternamn: " + lastName +
                "\nDatum: " + date +
                "\nMedlemskap:" + printPayingCustomer(payingCustomer);
    }
    public long getSsn() {
        return ssn;
    }

    public String getSurName() {
        return surName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDate() {
        return date;
    }

}
