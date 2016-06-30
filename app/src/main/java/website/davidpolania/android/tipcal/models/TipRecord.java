package website.davidpolania.android.tipcal.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by PC on 30/06/2016.
 */
public class TipRecord {
    private double bill;
    private int tipPercentage;
    private Date timestamp;

    public void setBill(double bill) {
        this.bill = bill;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getBill() {
        return bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getTip(){
        return bill*(tipPercentage/100d);
    }

    public String getDateFormatted(){
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        return simpleFormatter.format(timestamp);
    }

}
