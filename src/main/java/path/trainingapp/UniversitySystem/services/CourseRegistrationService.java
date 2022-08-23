package path.trainingapp.UniversitySystem.services;


import org.springframework.data.domain.Page;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationKeyDTO;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationPatchDTO;
import path.trainingapp.UniversitySystem.models.CourseRegistration;

import java.util.List;

public interface CourseRegistrationService {
    CourseRegistrationDTO registerStudents(CourseRegistrationDTO courseRegistrationDTO);

    List<CourseRegistrationDTO> getCourseBestGrades(int semester);

    CourseRegistrationDTO updateCourseRegistration(CourseRegistrationPatchDTO courseRegistrationPatchDTO);

    String deleteCourseRegistration(CourseRegistrationKeyDTO courseRegistrationKeyDTO);
}
