package path.trainingapp.UniversitySystem.services;


import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CourseStudentDTO;
import path.trainingapp.UniversitySystem.dto.CourseSubjectDTO;


import java.util.List;


public interface CourseService {

    List<CourseDTO> listCourses();

    String registerStudent(CourseStudentDTO courseStudentDTO);

    String registerSubject(CourseSubjectDTO courseSubjectDTO);

    CourseDTO saveCourse(CourseDTO courseDTO);

}
