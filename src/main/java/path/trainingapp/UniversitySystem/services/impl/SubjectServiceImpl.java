package path.trainingapp.UniversitySystem.services.impl;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.mapper.SubjectMapper;
import path.trainingapp.UniversitySystem.models.Subject;
import path.trainingapp.UniversitySystem.repositories.SubjectRepository;
import path.trainingapp.UniversitySystem.services.SubjectService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<SubjectDTO> listSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::subjectToSubjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Subject> getSubject(Long id) {
        return subjectRepository.findById(id);
    }
}
