package path.trainingapp.UniversitySystem.services;


import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CourseStudentDTO;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.repositories.StudentRepository;


import java.util.List;
import java.util.Optional;


public interface CourseService {

    List<CourseDTO> listCourses();

    String registerCourse(CourseStudentDTO courseStudentDTO);

}
