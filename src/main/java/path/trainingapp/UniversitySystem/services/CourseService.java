package path.trainingapp.UniversitySystem.services;


import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CoursePatchDTO;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.dto.CourseSubjectDTO;
import path.trainingapp.UniversitySystem.models.Course;


import java.util.List;
import java.util.Optional;


public interface CourseService {

    List<CourseDTO> listCourses();

    String registerSubject(CourseSubjectDTO courseSubjectDTO);

    CourseDTO saveCourse(CourseDTO courseDTO);

    Optional<Course> getCourse(Long id);

    CourseDTO getCourseDTO(Long id);

    CourseDTO updateCourse(CoursePatchDTO coursePatchDTO);

}
