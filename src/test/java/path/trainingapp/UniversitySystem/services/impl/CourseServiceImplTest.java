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
import path.trainingapp.UniversitySystem.dto.CourseSubjectDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
import path.trainingapp.UniversitySystem.mapper.CourseMapper;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.models.Subject;
import path.trainingapp.UniversitySystem.repositories.CourseRepository;
import path.trainingapp.UniversitySystem.services.StudentService;
import path.trainingapp.UniversitySystem.services.SubjectService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private CourseServiceImpl courseService;

    private CourseRegistrationDTO courseRegistrationDTO1;
    private CourseRegistrationDTO courseRegistrationDTO2;
    private CourseSubjectDTO courseSubjectDTO1;
    private Course course;
    private Subject subject1;

    @BeforeEach
    void setUp() {
        courseSubjectDTO1 = new CourseSubjectDTO(1L,2L);
        subject1 = new Subject(2L,"Testing with JUnit5", 4);
        course = new Course(1L,"testing",1);
    }

    @Test
    void registerSubjectSuccess() {
        String expected = "Subject added Successfully";
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Mockito.when(subjectService.getSubject(2L)).thenReturn(Optional.of(subject1));
        final String result = courseService.registerSubject(courseSubjectDTO1);
        Assertions.assertEquals(expected,result);
        Mockito.verify(courseRepository).findById(1L);
        Mockito.verify(subjectService).getSubject(2L);
    }
    @Test
    void registerSubjectCourseNotFound() {
        String expected = "Course not found";
        Mockito.when(subjectService.getSubject(2L)).thenReturn(Optional.of(subject1));
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> courseService.registerSubject(courseSubjectDTO1));
        Assertions.assertEquals(expected,exception.getMessage());
    }
    @Test
    void registerSubjectSubjectNotFound() {
        String expected = "Subject not found";
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> courseService.registerSubject(courseSubjectDTO1));
        Assertions.assertEquals(expected,exception.getMessage());
    }
}