package path.trainingapp.UniversitySystem.controllers;

import org.springframework.web.bind.annotation.*;
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.services.SubjectService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<SubjectDTO> listSubjects(){
        return subjectService.listSubjects();
    }

    @PostMapping("/save")
    public SubjectDTO saveSubject(SubjectDTO subjectDTO){
        return subjectService.saveSubject(subjectDTO);
    }

}
