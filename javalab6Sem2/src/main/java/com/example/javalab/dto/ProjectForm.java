package com.example.javalab.dto;

import com.example.javalab.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.PropertyMap;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

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
    Long worker_id;
}
