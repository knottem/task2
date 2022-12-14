package Program;

import java.time.LocalDate;

public class Customer {

    protected long ssn;
    protected String surName;
    protected String lastName;
    protected LocalDate date;
    protected boolean payingCustomer;

    public Customer(long ssn, String surName, String lastName, LocalDate date) {
        this.ssn = ssn;
        this.surName = surName;
        this.lastName = lastName;
        this.date = date;
    }

    private String printPayingCustomer(boolean payingCustomer){
        String paying;
        if(payingCustomer){
            paying = " Ja";
        }
        else{
            paying = " Nej";
        }
        return paying;
    }

    private String printSsn(long ssn){
        String a = String.valueOf(ssn);
        return new StringBuilder(a).insert(a.length()-4, "-").toString();
    }

    @Override
    public String toString() {
        return "Personnummer: " + printSsn(ssn) +
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPayingCustomer() {
        payingCustomer = true;
    }
    public String getLastName() {
        return lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isPayingCustomer() {
        return payingCustomer;
    }
    public String getPayingCustomer(){
        return printPayingCustomer(payingCustomer);
    }
}
