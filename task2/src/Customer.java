import java.time.LocalDate;

public class Customer {

    protected long ssn;
    protected String surName;
    protected String lastName;
    protected LocalDate date;

    public Customer(long ssn, String surName, String lastName, LocalDate date) {
        this.ssn = ssn;
        this.surName = surName;
        this.lastName = lastName;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Personnummer: " + ssn + "" +
                "\nFörnamn: " + surName +
                "\nEfternamn: " + lastName +
                "\nDatum: " + date +
                "\n";
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
