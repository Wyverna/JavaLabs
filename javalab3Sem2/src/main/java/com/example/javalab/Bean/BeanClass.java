package com.example.javalab.Bean;

import com.example.javalab.model.Worker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Configuration
@Component
@AllArgsConstructor
@NoArgsConstructor
public class BeanClass {
    private Worker worker;
    @Autowired(required = false)
    @Qualifier("StringResponse")
    String response="";
    public void printWorker()
   {
       System.out.println("Autowiring Qualifier: "+worker.getFirstName()+" "+worker.getLastName()+" "+getWorker().getAge());
   }
    public Worker getWorker() {
        return worker;
    }public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
