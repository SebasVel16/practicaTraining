package path.trainingapp.UniversitySystem.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import path.trainingapp.UniversitySystem.dto.CourseRegistrationDTO;
import path.trainingapp.UniversitySystem.services.CourseRegistrationService;

import javax.validation.Valid;

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
}
