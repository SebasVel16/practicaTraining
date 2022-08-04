package path.trainingapp.UniversitySystem.mapper;

import org.mapstruct.Mapper;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.models.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student studentDTOToStudent(StudentDTO studentDTO);
    StudentDTO studentToStudentDTO(Student student);
}
