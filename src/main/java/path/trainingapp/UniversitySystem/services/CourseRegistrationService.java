package path.trainingapp.UniversitySystem.services;


import org.springframework.data.domain.Page;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.models.CourseRegistration;

import java.util.List;

public interface CourseRegistrationService {
    CourseRegistrationDTO registerStudents(CourseRegistrationDTO courseRegistrationDTO);

    List<CourseRegistration> getCourseBestGrades(int semester);
}
