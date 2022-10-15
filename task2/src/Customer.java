import java.time.LocalDate;

public class Customer {

    protected long ssn;
    protected String surName;
    protected String lastName;
    protected LocalDate date;

    public long getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }


    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "Personnummer: " + ssn + "" +
                "\nFörnamn: " + surName +
                "\nEfternamn: " + lastName +
                "\nDatum: " + date;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Customer(long ssn, String surName, String lastName, LocalDate date) {
        this.ssn = ssn;
        this.surName = surName;
        this.lastName = lastName;
        this.date = date;
    }
}
