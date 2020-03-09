package models;

public class Producer {
    private String Producer;
    private String Country;

    public Producer(){

    }
    @Override
    public String toString(){
        if(this!=null) {
            return "Producer: \n"+
                    "\nProducer: "+ this.getProducer() +
                    "\nCountry: " + this.getCountry();
        }
        else return  "";
    }

    public Producer(String producer,String country){
        setProducer(producer);
        setCountry(country);
    }


    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        this.Producer = producer;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        this.Country = country;
    }
}
