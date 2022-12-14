package path.trainingapp.UniversitySystem.services.impl;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.dto.CourseSubjectDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
import path.trainingapp.UniversitySystem.mapper.CourseMapper;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.models.Subject;
import path.trainingapp.UniversitySystem.repositories.CourseRepository;
import path.trainingapp.UniversitySystem.services.CourseService;
import path.trainingapp.UniversitySystem.services.StudentService;
import path.trainingapp.UniversitySystem.services.SubjectService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl  implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final SubjectService subjectService;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper,
                             StudentService studentService, SubjectService subjectService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.subjectService = subjectService;
    }

    @Override
    public List<CourseDTO> listCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        Course course = courseMapper.courseDTOToCourse(courseDTO);
        return courseMapper.courseToCourseDTO(courseRepository.save(course));
    }

    @Override
    public Optional<Course> getCourse(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public String registerSubject(CourseSubjectDTO courseSubjectDTO) {
        Optional<Course> courseOpt = courseRepository.findById(courseSubjectDTO.getIdCourse());
        Optional<Subject> subjectOpt = subjectService.getSubject(courseSubjectDTO.getIdSubject());
        if(courseOpt.isPresent()){
            Course course = courseOpt.get();
            if(subjectOpt.isPresent()){
                course.addSubject(subjectOpt.get());
                courseRepository.save(course);
                return "Subject added Successfully";
            }else{
                throw new ResourceNotFoundException("Subject not found");
            }
        }
        throw new ResourceNotFoundException("Course not found");
    }


}
