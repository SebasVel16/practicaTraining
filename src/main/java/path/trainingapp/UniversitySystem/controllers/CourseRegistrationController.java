package path.trainingapp.UniversitySystem.controllers;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.models.CourseRegistration;
import path.trainingapp.UniversitySystem.services.CourseRegistrationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses/registration")
public class CourseRegistrationController {
    CourseRegistrationService courseRegistrationService;

    public CourseRegistrationController(CourseRegistrationService courseRegistrationService) {
        this.courseRegistrationService = courseRegistrationService;
    }
    @PostMapping("/save")
    public CourseRegistrationDTO registerStudents(@Valid @RequestBody CourseRegistrationDTO courseRegistrationDTO){
        return courseRegistrationService.registerStudents(courseRegistrationDTO);
    }
    @GetMapping("/best/{semester}")
    public List<CourseRegistrationDTO> getBestGrades(@PathVariable int semester){
        return courseRegistrationService.getCourseBestGrades(semester);
    }
}
