package path.trainingapp.UniversitySystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.services.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @MockBean
    StudentService studentService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    private List<StudentDTO> students = new ArrayList<>();
    private Student student;
    private StudentDTO studentDTO;
    private StudentDTO saveStudentDTO;


    @BeforeEach
    void setUp() {
        student = new Student(1L,"juan",30,"j@mail.com");
        studentDTO = new StudentDTO(1L,"j@mail.com","juan",30);
        saveStudentDTO = new StudentDTO(null,"j@mail.com","juan",30);
        students.add(studentDTO);
    }

    @Test
    void listStudents() throws Exception{
        Mockito.when(studentService.listStudents()).thenReturn(students);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)));
    }

    @Test
    void saveStudent() throws Exception{
        Mockito.when(studentService.saveStudent(saveStudentDTO)).thenReturn(studentDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(
                        this.mapper.writeValueAsString(saveStudentDTO)
                ))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name", is("juan")));
    }
}