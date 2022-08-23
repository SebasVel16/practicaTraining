package path.trainingapp.UniversitySystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPatchDTO implements Serializable {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @JsonProperty("age")
    private int age;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private  String email;
}
