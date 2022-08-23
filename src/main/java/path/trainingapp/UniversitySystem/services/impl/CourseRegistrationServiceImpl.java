package path.trainingapp.UniversitySystem.services.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationKeyDTO;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationPatchDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
import path.trainingapp.UniversitySystem.mapper.CourseRegistrationMapper;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.models.CourseRegistration;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.models.compositeKey.CourseRegistrationKey;
import path.trainingapp.UniversitySystem.repositories.CourseRegistrationRepository;
import path.trainingapp.UniversitySystem.services.CourseRegistrationService;
import path.trainingapp.UniversitySystem.services.CourseService;
import path.trainingapp.UniversitySystem.services.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    CourseRegistrationRepository courseRegistrationRepository;
    CourseRegistrationMapper courseRegistrationMapper;
    CourseService courseService;
    StudentService studentService;

    public CourseRegistrationServiceImpl(CourseRegistrationRepository courseRegistrationRepository,
                                         CourseService courseService,
                                         StudentService studentService,
                                         CourseRegistrationMapper courseRegistrationMapper) {
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.courseService = courseService;
        this.studentService = studentService;
        this.courseRegistrationMapper = courseRegistrationMapper;
    }

    @Override
    public CourseRegistrationDTO registerStudents(CourseRegistrationDTO courseRegistrationDTO) {
        CourseRegistration courseRegistration = courseRegistrationMapper.dtoToCourseRegistration(courseRegistrationDTO);
        Optional<Course> course = courseService.getCourse(courseRegistrationDTO.getCourseId());
        Optional<Student> student = studentService.getStudent(courseRegistrationDTO.getStudentId());
        if(course.isPresent()){
            if(course.get().getCapacity() >= course.get().getRegistrations().size()) {
                courseRegistration.setCourse(course.get());
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Course is full");
            }
        }
        else {
            throw new ResourceNotFoundException("Course not found");
        }
        if(student.isPresent()){
            courseRegistration.setStudent(student.get());
        }
        else {
            throw new ResourceNotFoundException("Student not found");
        }
        return courseRegistrationMapper.courseRegistrationToDTO(courseRegistrationRepository.save(courseRegistration));
    }

    @Override
    public List<CourseRegistrationDTO> getCourseBestGrades(int semester) {
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository
                .findTop5BySemesterOrderByGradeDesc(semester);
        List<CourseRegistrationDTO> courseRegistrationDTOS = new ArrayList<>();
        for (CourseRegistration courseRegistration : courseRegistrations) {
            courseRegistrationDTOS.add(courseRegistrationMapper.courseRegistrationToDTO(courseRegistration));
        }
        return courseRegistrationDTOS;
    }

    @Override
    public CourseRegistrationDTO updateCourseRegistration(CourseRegistrationPatchDTO courseRegistrationPatchDTO) {
        CourseRegistrationKey courseRegistrationKey = new CourseRegistrationKey(
                courseRegistrationPatchDTO.getCourseId(), courseRegistrationPatchDTO.getStudentId()
        );
        Optional<CourseRegistration> courseRegistration = courseRegistrationRepository.findById(courseRegistrationKey);

        if (courseRegistration.isPresent()){
            int oldSemester = courseRegistration.get().getSemester();
            int newSemester = courseRegistrationPatchDTO.getSemester();
            double oldGrade = courseRegistration.get().getGrade();
            double newGrade = courseRegistrationPatchDTO.getGrade();

            if (newGrade != 0 && newGrade != oldGrade) {
                courseRegistration.get().setGrade(courseRegistrationPatchDTO.getGrade());
            }
            if (newSemester != 0 && newSemester != oldSemester){
                courseRegistration.get().setSemester(courseRegistrationPatchDTO.getSemester());
            }
            return courseRegistrationMapper.courseRegistrationToDTO(courseRegistrationRepository.save(courseRegistration.get()));
        }else{
            throw new ResourceNotFoundException("Course not found");
        }
    }

    @Override
    public String deleteCourseRegistration(CourseRegistrationKeyDTO courseRegistrationKeyDTO) {
        CourseRegistrationKey courseRegistrationKey = new CourseRegistrationKey(
                courseRegistrationKeyDTO.getCourseId(), courseRegistrationKeyDTO.getStudentId()
        );
        Optional<CourseRegistration> courseRegistration = courseRegistrationRepository.findById(courseRegistrationKey);
        if(courseRegistration.isPresent()){
            courseRegistrationRepository.delete(courseRegistration.get());
            return "Record deleted successfully";
        }
        else {
            throw new ResourceNotFoundException("Record not found");
        }
    }
}
