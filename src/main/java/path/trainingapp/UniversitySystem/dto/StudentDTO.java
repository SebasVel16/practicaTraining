package path.trainingapp.UniversitySystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {

    @Null
    @JsonProperty("id")
    private Long id;

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
