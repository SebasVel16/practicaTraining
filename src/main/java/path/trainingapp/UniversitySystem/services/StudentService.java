package path.trainingapp.UniversitySystem.services;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface StudentService {
    List<StudentDTO> listStudents();
    Optional<Student> getStudent(Long id);
    StudentDTO saveStudent(StudentDTO studentDTO);
}
