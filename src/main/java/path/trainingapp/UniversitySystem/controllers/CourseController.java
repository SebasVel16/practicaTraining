package path.trainingapp.UniversitySystem.controllers;

import org.springframework.web.bind.annotation.*;
import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CourseSubjectDTO;
import path.trainingapp.UniversitySystem.services.CourseService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public List<CourseDTO> listAllCourses(){
        return courseService.listCourses();
    }

    @PostMapping("/save")
    public CourseDTO saveCourse(@Valid @RequestBody CourseDTO courseDTO){
        return courseService.saveCourse(courseDTO);
    }


    @PostMapping("/register-subjects")
    public String registerSubject(@Valid @RequestBody CourseSubjectDTO courseSubjectDTO){
            return courseService.registerSubject(courseSubjectDTO);
    }

    @GetMapping("{id}")
    public CourseDTO getCourseById(@PathVariable Long id){
       return courseService.getCourseDTO(id);
    }

}
