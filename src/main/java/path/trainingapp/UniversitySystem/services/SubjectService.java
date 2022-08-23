package path.trainingapp.UniversitySystem.services;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.dto.SubjectPatchDTO;
import path.trainingapp.UniversitySystem.models.Subject;
import path.trainingapp.UniversitySystem.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface SubjectService {

    List<SubjectDTO> listSubjects();
    Optional<Subject> getSubject(Long id);
    SubjectDTO saveSubject(SubjectDTO subjectDTO);
    SubjectDTO updateSubject(SubjectPatchDTO subjectPatchDTO);
    String deleteSubject(Long id);

}
