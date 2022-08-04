package path.trainingapp.UniversitySystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseListDTO {
    List<CourseDTO> courses;
}
