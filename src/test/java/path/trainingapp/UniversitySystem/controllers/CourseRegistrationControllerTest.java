package path.trainingapp.UniversitySystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.services.CourseRegistrationService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CourseRegistrationController.class)
class CourseRegistrationControllerTest {

    @MockBean
    CourseRegistrationService courseRegistrationService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    private CourseRegistrationDTO courseRegistrationDTO;
    private CourseRegistrationDTO courseRegistrationDTO2;
    private final List<CourseRegistrationDTO> courseRegistrationDTOList = new ArrayList<>();
    private int semester;

    @BeforeEach
    void setUp() {
        semester = 202202;
        courseRegistrationDTO = new CourseRegistrationDTO(1L,2L,3,semester);
        courseRegistrationDTO2 = new CourseRegistrationDTO(1L,3L,5,semester);
        courseRegistrationDTOList.add(courseRegistrationDTO);
        courseRegistrationDTOList.add(courseRegistrationDTO2);
        Collections.sort(courseRegistrationDTOList, new Comparator<>() {
            @Override
            public int compare(CourseRegistrationDTO o1, CourseRegistrationDTO o2) {
                return Double.compare(o2.getGrade(), o1.getGrade());
            }
        });
    }

    @Test
    void registerStudents() throws Exception {
        Mockito.when(courseRegistrationService.registerStudents(courseRegistrationDTO))
                .thenReturn(courseRegistrationDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/courses/registration/save")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(courseRegistrationDTO)))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.grade",is(3.0)));

    }

    @Test
    void getBestGrades() throws Exception {
        Mockito.when(courseRegistrationService.getCourseBestGrades(semester))
                .thenReturn(courseRegistrationDTOList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/registration/best/202202")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$[0].grade",is(5.0)));

    }
}