package path.trainingapp.UniversitySystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseSubjectDTO implements Serializable {

    @JsonProperty("idCourse")
    private Long idCourse;

    @JsonProperty("idSubject")
    private Long idSubject;
}
