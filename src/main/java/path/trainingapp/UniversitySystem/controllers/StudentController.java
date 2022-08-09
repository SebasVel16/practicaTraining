package path.trainingapp.UniversitySystem.controllers;

import org.springframework.web.bind.annotation.*;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.services.StudentService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> listStudents(){
        return studentService.listStudents();
    }

    @PostMapping("/save")
    public StudentDTO saveStudent(@Valid @RequestBody StudentDTO studentDTO){
        return studentService.saveStudent(studentDTO);
    }

}
