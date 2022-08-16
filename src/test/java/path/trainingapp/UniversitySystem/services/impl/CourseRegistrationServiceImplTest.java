package path.trainingapp.UniversitySystem.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
import path.trainingapp.UniversitySystem.mapper.CourseRegistrationMapper;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.models.CourseRegistration;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.models.compositeKey.CourseRegistrationKey;
import path.trainingapp.UniversitySystem.repositories.CourseRegistrationRepository;
import path.trainingapp.UniversitySystem.services.CourseService;
import path.trainingapp.UniversitySystem.services.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseRegistrationServiceImplTest {

    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;

    @Mock
    private CourseService courseService;

    @Mock
    private StudentService studentService;

    @Mock
    private CourseRegistrationMapper courseRegistrationMapper;

    @InjectMocks
    private CourseRegistrationServiceImpl courseRegistrationService;

    private CourseRegistration courseRegistration;
    private Course course;
    private Student student;
    private CourseRegistrationDTO courseRegistrationDTO;
    private CourseRegistrationKey courseRegistrationKey;
    private final List<CourseRegistration> courseRegistrations = new ArrayList<>();



    @BeforeEach
    void setUp() {
        student = new Student(2L,"José",22,"Jose@mail.com");
        course = new Course(1L,"testing",1);
        courseRegistrationKey = new CourseRegistrationKey(1L,2L);
        courseRegistration = new CourseRegistration(courseRegistrationKey,student,course,5,202202);
        courseRegistrationDTO = new CourseRegistrationDTO(1L,2L,5,202202);
        courseRegistrations.add(courseRegistration);
    }

    @Test
    void registerStudentsSuccess() {
        CourseRegistrationDTO expected = new CourseRegistrationDTO(1L,2L,5,202202);
        Mockito.when(courseRegistrationMapper.dtoToCourseRegistration(courseRegistrationDTO))
                .thenReturn(courseRegistration);
        Mockito.when(courseRegistrationRepository.save(courseRegistration)).thenReturn(courseRegistration);
        Mockito.when(courseRegistrationMapper.courseRegistrationToDTO(courseRegistration)).thenReturn(courseRegistrationDTO);
        Mockito.when(courseService.getCourse(1L)).thenReturn(Optional.of(course));
        Mockito.when(studentService.getStudent(2L)).thenReturn(Optional.of(student));
        final CourseRegistrationDTO result = courseRegistrationService.registerStudents(courseRegistrationDTO);
        Assertions.assertEquals(expected,result);
    }
    @Test
    void registerStudentsCourseNotFound(){
        String expected = "Course not found";
        Mockito.when(courseRegistrationMapper.dtoToCourseRegistration(courseRegistrationDTO))
                .thenReturn(courseRegistration);
        Mockito.when(studentService.getStudent(courseRegistrationDTO.getStudentId())).thenReturn(Optional.of(student));
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> courseRegistrationService.registerStudents(courseRegistrationDTO));
        Assertions.assertEquals(expected,exception.getMessage());
    }

    @Test
    void registerStudentsStudentNotFound(){
        String expected = "Student not found";
        Mockito.when(courseRegistrationMapper.dtoToCourseRegistration(courseRegistrationDTO))
                .thenReturn(courseRegistration);
        Mockito.when(courseService.getCourse(courseRegistrationDTO.getCourseId())).thenReturn(Optional.of(course));
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> courseRegistrationService.registerStudents(courseRegistrationDTO));
        Assertions.assertEquals(expected,exception.getMessage());
    }


    @Test
    void getCourseBestGrades() {
        List<CourseRegistrationDTO> expected = new ArrayList<>();
        expected.add(courseRegistrationDTO);
        Mockito.when(courseRegistrationRepository.findTop5BySemesterOrderByGradeDesc(202202)).thenReturn(courseRegistrations);
        Mockito.when(courseRegistrationMapper.courseRegistrationToDTO(courseRegistration)).thenReturn(courseRegistrationDTO);
        final List<CourseRegistrationDTO> result = courseRegistrationService.getCourseBestGrades(202202);
        Assertions.assertEquals(expected,result);
    }
}