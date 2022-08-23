package path.trainingapp.UniversitySystem.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRegistrationKeyDTO {

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("student_id")
    private Long studentId;
}
