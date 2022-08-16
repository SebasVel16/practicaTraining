package path.trainingapp.UniversitySystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.descriptor.web.FragmentJarScannerCallback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CourseSubjectDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.services.CourseService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @MockBean
    CourseService courseService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;


    private List<CourseDTO> courses;
    private Course course;
    private CourseDTO courseDTO;
    private CourseDTO saveCourseDto;
    private CourseSubjectDTO courseSubjectDTO;


    @BeforeEach
    void setUp(){
        courses = new ArrayList<>();
        course = new Course(1L,"test1",1);
        courseDTO = new CourseDTO(1L,"test1",1);
        saveCourseDto = new CourseDTO(null,"test1",1);
        courseSubjectDTO = new CourseSubjectDTO(1L,2L);
        courses.add(courseDTO);
    }
    @Test
    void listAllCourses() throws Exception {
        Mockito.when(courseService.listCourses()).thenReturn(courses);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }
    @Test
    void getCourseById() throws Exception {
        Mockito.when(courseService.getCourseDTO(1L)).thenReturn(courseDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name", is("test1")));
    }
    @Test
    void saveCourse() throws Exception {
        Mockito.when(courseService.saveCourse(saveCourseDto)).thenReturn(courseDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/courses/save")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(
                        this.mapper.writeValueAsString(saveCourseDto)
                        ))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.name",is("test1")));
    }
    @Test
    void registerSubject() throws Exception{
        Mockito.when(courseService.registerSubject(courseSubjectDTO)).thenReturn("Subject added Successfully");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/courses/register-subjects")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(courseSubjectDTO)))
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$",is("Subject added Successfully")));
    }
}
