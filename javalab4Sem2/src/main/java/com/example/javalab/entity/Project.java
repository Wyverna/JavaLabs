package com.example.javalab.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Projects")
public class Project {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "Project_Head")
    private String firstName;
    @Column(name = "nameProject")
    private String nameProject;
    public Project(long id,String firstName,String nameProject)
    {
        setId(id);
        setFirstName(firstName);
        setNameProject(nameProject);
    }
    @ManyToOne
    @JoinColumn(name="Worker_ID")
    private Worker worker=new Worker();
}
