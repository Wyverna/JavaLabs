package com.example.javalab.form;

import com.example.javalab.Validator.CellPhone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerForm implements Serializable {
    @NotBlank(message="{valid.firstName.notBlank}")
    @Size(min=3, message="{valid.firstname.size.min3}")
    private String firstName;
    @NotBlank(message="{valid.lastName.notBlank}")
    @Size(min=3, message="{valid.lastname.size.min3}")
    private String lastName;
    @NotNull(message="{valid.experienceWork.notBlank}")
    @Min(5)
    private Integer experienceWork;
    @NotBlank(message="{valid.technologies.notBlank}")
    private String technologies;
    @DateTimeFormat(style="S-")
    @Past(message="{valid.birthday.past}")
    private Date birthday;
    @NotBlank(message="{valid.position.notBlank}")
    private String position;
    @NotNull(message = "{valid.age.notBlank}")
    @Min(18)
    private Integer age;
    @CellPhone(message = "{valid.phone.cellphone}")
    private String telephone;
}
