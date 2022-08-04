package path.trainingapp.UniversitySystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class CourseDTO implements Serializable {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("capacity")
    private int capacity;

    @JsonProperty("students")
    private Set<StudentDTO> students;

    @JsonProperty("subjects")
    private Set<SubjectDTO> subjects;
}
