package path.trainingapp.UniversitySystem.services.impl;

import org.apache.logging.log4j.message.StringFormattedMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import path.trainingapp.UniversitySystem.dto.CourseStudentDTO;
import path.trainingapp.UniversitySystem.dto.CourseSubjectDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
import path.trainingapp.UniversitySystem.mapper.CourseMapper;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.models.Subject;
import path.trainingapp.UniversitySystem.repositories.CourseRepository;
import path.trainingapp.UniversitySystem.services.CourseService;
import path.trainingapp.UniversitySystem.services.StudentService;
import path.trainingapp.UniversitySystem.services.SubjectService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private StudentService studentService;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private CourseServiceImpl courseService;

    private CourseStudentDTO courseStudentDTO1;
    private CourseStudentDTO courseStudentDTO2;
    private CourseSubjectDTO courseSubjectDTO1;
    private Course course;
    private Course course1;
    private Student student1;
    private Student student2;
    private Subject subject1;

    @BeforeEach
    void setUp() {
        courseStudentDTO1 = new CourseStudentDTO(1L,2L);
        courseStudentDTO2 = new CourseStudentDTO(4L,3L);
        courseSubjectDTO1 = new CourseSubjectDTO(1L,2L);
        subject1 = new Subject(2L,"Testing with JUnit5", 4);
        course = new Course(1L,"testing",1);
        course1 = new Course(4L,"HelloWorld",0);
        student1 = new Student(2L,"Juan",20);
        student2 = new Student(3L,"Pepe",20);

    }


    @Test
    void registerStudentSuccess() {
        String expected = "Course added Successfully";
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Mockito.when(studentService.getStudent(2L)).thenReturn(Optional.of(student1));
        final String result = courseService.registerStudent(courseStudentDTO1);
        Assertions.assertEquals(result,expected);
        Mockito.verify(courseRepository).findById(1L);
        Mockito.verify(studentService).getStudent(2L);
    }
    @Test
    void registerStudentCourseNotFound() {
        String expected = "Course not found";
        Mockito.when(studentService.getStudent(2L)).thenReturn(Optional.of(student1));
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> courseService.registerStudent(courseStudentDTO1));
        String error = exception.getMessage();
        assertEquals(expected,error);
        Mockito.verify(studentService).getStudent(2L);
    }

    @Test
    void registerStudentStudentNotFound() {
        String expected = "Student not found";
        Mockito.when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> courseService.registerStudent(courseStudentDTO1));
        String error = exception.getMessage();
        assertEquals(expected,error);
        Mockito.verify(courseRepository).findById(1L);
    }
    @Test
    void registerStudentCourseFull() {
        String expected = "Course full";
        Mockito.when(courseRepository.findById(4L)).thenReturn(Optional.of(course1));
        Mockito.when(studentService.getStudent(3L)).thenReturn(Optional.of(student2));
        final String result = courseService.registerStudent(courseStudentDTO2);
        Assertions.assertEquals(expected,result);
        Mockito.verify(courseRepository).findById(4L);
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