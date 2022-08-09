package path.trainingapp.UniversitySystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class StudentDTO implements Serializable {

    @JsonProperty("id")
    private long id;

    @Email
    @NotNull(message = "Email is mandatory")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "Name is mandatory")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Age is mandatory")
    @JsonProperty("age")
    private int age;


}
