package path.trainingapp.UniversitySystem.services.impl;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CourseStudentDTO;
import path.trainingapp.UniversitySystem.mapper.CourseMapper;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.repositories.CourseRepository;
import path.trainingapp.UniversitySystem.repositories.StudentRepository;
import path.trainingapp.UniversitySystem.services.CourseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl  implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.studentRepository = studentRepository;
    }


    @Override
    public List<CourseDTO> listCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String registerCourse(CourseStudentDTO courseStudentDTO) {
        Optional<Course> courseOp = courseRepository.findById(courseStudentDTO.getIdCourse());
        Optional<Student> studentOp = studentRepository.findById(courseStudentDTO.getIdStudent());
        if(!courseOp.isEmpty()){
            Course course = (Course) courseOp.get();
            if(!studentOp.isEmpty()){
                Student student = (Student) studentOp.get();
                course.getStudents().add(student);
                courseRepository.save(course);
                return "Course added Successfully";
            }
            return "Student with this id does not exist";
        }
        return "Course with this id does not exit";
    }


}
