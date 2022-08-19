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
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.models.Subject;
import path.trainingapp.UniversitySystem.services.SubjectService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(SubjectController.class)
class SubjectControllerTest {
    @MockBean
    SubjectService subjectService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    private List<SubjectDTO> subjects = new ArrayList<>();
    private Subject subject;
    private SubjectDTO subjectDTO;
    private SubjectDTO saveSubjectDTO;

    @BeforeEach
    void setUp() {
        subject = new Subject(1L,"matematicas",4);
        subjectDTO = new SubjectDTO(1L,"matematicas",4);
        saveSubjectDTO = new SubjectDTO(null,"matematicas",4);
        subjects.add(subjectDTO);
    }

    @Test
    void listSubjects() throws Exception{
        Mockito.when(subjectService.listSubjects()).thenReturn(subjects);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/subjects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)));
    }

    @Test
    void saveSubject() throws Exception{
        Mockito.when(subjectService.saveSubject(saveSubjectDTO)).thenReturn(subjectDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/subjects")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(
                        this.mapper.writeValueAsString(saveSubjectDTO)
                ))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name",is("matematicas")));
    }
}