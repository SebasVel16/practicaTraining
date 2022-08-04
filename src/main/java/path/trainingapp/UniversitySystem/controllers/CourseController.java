package path.trainingapp.UniversitySystem.controllers;

import org.springframework.web.bind.annotation.*;
import path.trainingapp.UniversitySystem.dto.CourseDTO;
import path.trainingapp.UniversitySystem.dto.CourseStudentDTO;
import path.trainingapp.UniversitySystem.repositories.StudentRepository;
import path.trainingapp.UniversitySystem.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

//    @GetMapping
//    public ResponseEntity<CourseListDTO> getAllCourses(){
//        return new ResponseEntity<CourseListDTO>(
//                new CourseListDTO(courseService.listCourses()), HttpStatus.OK
//                );
//    }

    @GetMapping
    public List<CourseDTO> listAllCourses(){
        System.out.println("Hola");
        return courseService.listCourses();
    }

    @PostMapping("/register")
    public String registerCourse(@RequestBody CourseStudentDTO courseStudentDTO){
        return courseService.registerCourse(courseStudentDTO);
    }

}
