package path.trainingapp.UniversitySystem.controllers;

import org.springframework.web.bind.annotation.*;
import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CourseSubjectDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
import path.trainingapp.UniversitySystem.mapper.CourseMapper;
import path.trainingapp.UniversitySystem.models.Course;
import path.trainingapp.UniversitySystem.services.CourseService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
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
       Optional<Course> course = courseService.getCourse(id);
       if(course.isPresent()){
           return courseMapper.courseToCourseDTO(course.get());
       }else {
           throw new ResourceNotFoundException("Course not found");
       }
    }

}
