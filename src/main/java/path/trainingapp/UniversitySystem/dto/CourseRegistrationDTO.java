package path.trainingapp.UniversitySystem.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CourseRegistrationDTO implements Serializable {


    @NotNull
    @JsonProperty("course_id")
    private Long courseId;


    @NotNull
    @JsonProperty("student_id")
    private Long studentId;

    @NotNull
    @Max(value = 5, message = "Value must be less than or equals to 5")
    @Min(value = 0, message = "value must be greater or equal to 0")
    @JsonProperty("grade")
    private double grade;

    @NotNull
    @JsonProperty("semester")
    private int semester;
}
