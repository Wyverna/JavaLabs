package com.example.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForm {
    long id;
    @NotBlank(message="{valid.firstName.notBlank}")
    @Size(min=3, message="{valid.firstname.size.min3}")
    private String firstName;
    @NotBlank(message="{valid.firstName.notBlank}")
    @Size(min=3, message="{valid.firstname.size.min3}")
    private String nameProject;
    @NotNull(message="{valid.Worker_id.notBlank}")
    Long worker_Id;
}
