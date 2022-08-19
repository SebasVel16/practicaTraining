package path.trainingapp.UniversitySystem.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.mapper.StudentMapper;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.repositories.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        student = new Student(1L,"juan",30,"juan@mail.com");
        studentDTO = new StudentDTO(1L,"juan@mail.com","juan",30);
    }


    @Test
    void getStudent() {
        Optional<Student> expected = Optional.of(student);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        final Optional<Student> result = studentService.getStudent(1L);
        Assertions.assertEquals(expected,result);
        Mockito.verify(studentRepository).findById(1L);
    }

    @Test
    void saveStudent() {
        StudentDTO expected = new StudentDTO(1L,"juan@mail.com","juan",30);
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Mockito.when((studentMapper.studentDTOToStudent(studentDTO))).thenReturn(student);
        Mockito.when(studentMapper.studentToStudentDTO(student)).thenReturn(studentDTO);
        final StudentDTO result = studentService.saveStudent(studentDTO);
        Assertions.assertEquals(expected,result);
        Mockito.verify(studentRepository).save(student);
    }
}