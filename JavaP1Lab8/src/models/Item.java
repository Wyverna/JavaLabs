package models;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private String Request;
    private String DateIssue;
    private String Producer;
    private int Cost;

    public String getName() {
        return name;
    }
    public Item(){
    }
    public Item(String name, String request, String dateIssue, String producer, int cost){
        this.setName(name);
        this.setRequest(request);
        this.setDateIssue(dateIssue);
        this.setProducer(producer);
        this.setCost(cost);
    }

    public void setName(String name) { this.name = name; }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String request) {
        this.Request = request;
    }

    public String getProducer() {
        return Producer;
    }

    public String getDateIssue() {
        return DateIssue;
    }

    public int getCost() {
        return Cost;
    }

    public void setDateIssue(String dateIssue) {this.DateIssue = dateIssue;}

    public void setProducer(String producer) {
        this.Producer = producer;
    }

    public void setCost(int cost) {this.Cost = cost;}
    @Override
    public String toString(){
        if(this!=null){
            return "\nItem:\n" +
                    "Name: "+this.getName()+
                    "\nRequest: "+this.getRequest()+
                    "\nDateIssue "+this.getDateIssue()+
                    "\nProducer:"+this.getProducer()+
                    "\nCost:"+this.getCost();
        }
        return "";
    }
}
