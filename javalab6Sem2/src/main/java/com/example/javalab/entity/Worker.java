package com.example.javalab.entity;

import javax.persistence.*;

import lombok.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Workers")
public class Worker implements InitializingBean, DisposableBean {
    @Id
    @Column(name = "Worker_ID")
    private long id;
    @Column(name = "Worker_First_Name")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "ExperienceWork")
    private Integer experienceWork;
    @Column(name = "Technologies")
    private String technologies;
    @Column(name = "BirthDay")
    private Date birthday;
    @Column(name = "Position")
    private String position;
    @Column(name = "Age")
    private Integer age;
    @Column(name = "Telephone")
    private String telephone;
    public Worker(long id,String firstName,String lastName,Integer experienceWork,String technologies,
    Date birthday,String position,Integer age,String telephone)
    {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setExperienceWork(experienceWork);
        setTechnologies(technologies);
        setBirthday(birthday);
        setPosition(position);
        setAge(age);
        setTelephone(telephone);
    }
    @OneToMany(mappedBy = "worker",cascade = CascadeType.ALL)
    private Set<Project> projects;
    public void afterPropertiesSet() throws Exception {
        System.out.println("Init method after properties are set : " + getFirstName());
    }

    public void destroy() throws Exception {
        System.out.println("Spring Worker is destroy! Worker clean up");
    }
}
