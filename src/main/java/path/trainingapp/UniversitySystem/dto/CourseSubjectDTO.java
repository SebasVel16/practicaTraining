package path.trainingapp.UniversitySystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CourseSubjectDTO implements Serializable {

    @NotNull
    @JsonProperty("idCourse")
    private Long idCourse;

    @NotNull
    @JsonProperty("idSubject")
    private Long idSubject;
}
