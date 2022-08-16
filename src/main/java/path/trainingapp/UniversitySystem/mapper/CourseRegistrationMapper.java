package path.trainingapp.UniversitySystem.mapper;


import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.models.CourseRegistration;


public interface CourseRegistrationMapper {
    CourseRegistration dtoToCourseRegistration(CourseRegistrationDTO courseRegistrationDTO);
    CourseRegistrationDTO courseRegistrationToDTO(CourseRegistration courseRegistration);
}
