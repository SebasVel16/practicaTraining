package path.trainingapp.UniversitySystem.services.impl;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.dto.SubjectPatchDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
import path.trainingapp.UniversitySystem.mapper.SubjectMapper;
import path.trainingapp.UniversitySystem.models.Student;
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

    @Override
    public SubjectDTO saveSubject(SubjectDTO subjectDTO) {
        Subject subject = subjectMapper.subjectDTOToSubject(subjectDTO);
        return subjectMapper.subjectToSubjectDTO(subjectRepository.save(subject));
    }

    @Override
    public SubjectDTO updateSubject(SubjectPatchDTO subjectPatchDTO) {
        Optional<Subject> subject = subjectRepository.findById(subjectPatchDTO.getId());
        int newCredits = subjectPatchDTO.getCredits();
        if (subject.isPresent()){
            if (newCredits != 0) {
                subject.get().setCredits(newCredits);
            }
            if (subjectPatchDTO.getName() != null){
                subject.get().setName(subjectPatchDTO.getName());
            }

            return subjectMapper.subjectToSubjectDTO(subjectRepository.save(subject.get()));
        }else{
            throw new ResourceNotFoundException("Subject not found");
        }
    }

    @Override
    public String deleteSubject(Long id) {
        if (subjectRepository.existsById(id)){
            subjectRepository.deleteById(id);
            return "Subject deleted successfully";
        }else {
            throw new ResourceNotFoundException("Subject not found");
        }
    }
}
