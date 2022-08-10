package path.trainingapp.UniversitySystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
public class CourseDTO implements Serializable {


    @Null
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "Name is mandatory")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Capacity is mandatory")
    @JsonProperty("capacity")
    private int capacity;

}
