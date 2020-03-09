package models;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private Date Year;
    private int Sum;
    public Date getYear() {
        return Year;
    }
    public User(){
    }
    public User(Date year, int sum){
        this.setYear(year);
        this.setSum(sum);
    }

    public void setYear(Date year) { this.Year = year; }

    public int getSum() {
        return Sum;
    }

    public void setSum(int sum) {
        this.Sum = sum;
    }
    @Override
    public String toString(){
        if(this!=null){
            return "\nUser:\n" +
                    "Year: "+this.getYear()+
                    "\nSum: "+this.getSum();
        }
        return "";
    }
}
