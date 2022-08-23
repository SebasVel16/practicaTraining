package path.trainingapp.UniversitySystem.controllers;

import org.springframework.web.bind.annotation.*;
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.dto.SubjectPatchDTO;
import path.trainingapp.UniversitySystem.services.SubjectService;

import javax.validation.Valid;
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

    @PostMapping
    public SubjectDTO saveSubject(@Valid @RequestBody SubjectDTO subjectDTO){
        return subjectService.saveSubject(subjectDTO);
    }

    @PatchMapping
    public SubjectDTO updateSubject(@Valid @RequestBody SubjectPatchDTO subjectPatchDTO){
        return subjectService.updateSubject(subjectPatchDTO);
    }

    @DeleteMapping("{id}")
    public String deleteSubject(@PathVariable Long id){
        return subjectService.deleteSubject(id);
    }

}
