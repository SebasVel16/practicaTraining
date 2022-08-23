package path.trainingapp.UniversitySystem.services.impl;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CoursePatchDTO;
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
    public CourseDTO getCourseDTO(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return courseMapper.courseToCourseDTO(course.get());
        }else {
            throw new ResourceNotFoundException("Course not found");
        }
    }

    @Override
    public CourseDTO updateCourse(CoursePatchDTO coursePatchDTO) {
        Optional<Course> course = courseRepository.findById(coursePatchDTO.getId());
        if(course.isPresent()){
            if(coursePatchDTO.getName() != null){
                course.get().setName(coursePatchDTO.getName());
            }
            if (course.get().getCapacity() != 0 && coursePatchDTO.getCapacity() != 0){
                course.get().setCapacity(coursePatchDTO.getCapacity());
            }
            courseRepository.save(course.get());
            return courseMapper.courseToCourseDTO(course.get());
        }else{
            throw new ResourceNotFoundException("Course not found");
        }
    }

    @Override
    public String deleteCourse(Long id) {
        if(courseRepository.existsById(id)){
            courseRepository.deleteById(id);
            return "Course deleted successfully";
        }
        else {
            throw new ResourceNotFoundException("Course not found");
        }
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
