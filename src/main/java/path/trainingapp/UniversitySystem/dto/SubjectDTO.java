package path.trainingapp.UniversitySystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class SubjectDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @NotNull(message = "Name is mandatory")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Credits are mandatory")
    @JsonProperty("credits")
    private int credits;
}
