package com.example.javalab.model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
@Getter
@Setter
@Data
@AllArgsConstructor
public class Worker implements InitializingBean, DisposableBean {
    private String firstName;
    private String lastName;
    private Integer experienceWork;
    private String technologies;
    private Date birthday;
    private String position;
    private Integer age;
    private String telephone;
    public void afterPropertiesSet() throws Exception {
        System.out.println("Init method after properties are set : " + getFirstName());
    }

    public void destroy() throws Exception {
        System.out.println("Spring Worker is destroy! Worker clean up");
    }
}
