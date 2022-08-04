package path.trainingapp.UniversitySystem.services;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;


public interface StudentService {

    List<StudentDTO> listStudents();
}
