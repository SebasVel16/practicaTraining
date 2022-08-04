package path.trainingapp.UniversitySystem.services;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.models.Subject;
import path.trainingapp.UniversitySystem.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;


public interface SubjectService {

    List<SubjectDTO> listSubjects();

}
