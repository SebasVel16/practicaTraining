package path.trainingapp.UniversitySystem.mapper;

import org.mapstruct.Mapper;
import path.trainingapp.UniversitySystem.dto.SubjectDTO;
import path.trainingapp.UniversitySystem.models.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject subjectDTOToSubject(SubjectDTO subjectDTO);
    SubjectDTO subjectToSubjectDTO(Subject subject);
}
