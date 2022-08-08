package path.trainingapp.UniversitySystem.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@Data
public class CourseStudentDTO implements Serializable {


    @JsonProperty("idCourse")
    private Long idCourse;


    @JsonProperty("idStudent")
    private Long idStudent;
}
