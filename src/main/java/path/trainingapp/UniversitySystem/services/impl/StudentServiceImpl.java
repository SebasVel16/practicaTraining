package path.trainingapp.UniversitySystem.services.impl;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.mapper.StudentMapper;
import path.trainingapp.UniversitySystem.models.Student;
import path.trainingapp.UniversitySystem.repositories.StudentRepository;
import path.trainingapp.UniversitySystem.services.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDTO> listStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }
}
