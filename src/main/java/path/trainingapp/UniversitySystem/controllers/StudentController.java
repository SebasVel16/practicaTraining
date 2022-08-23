package path.trainingapp.UniversitySystem.controllers;

import org.springframework.web.bind.annotation.*;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.dto.StudentPatchDTO;
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

    @PostMapping
    public StudentDTO saveStudent(@Valid @RequestBody StudentDTO studentDTO){
        return studentService.saveStudent(studentDTO);
    }

    @PatchMapping
    public StudentDTO updateStudent(@Valid @RequestBody StudentPatchDTO studentPatchDTO){
        return studentService.updateStudent(studentPatchDTO);
    }

    @DeleteMapping("{id}")
    public String deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

}
