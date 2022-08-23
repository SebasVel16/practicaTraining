package path.trainingapp.UniversitySystem.services.impl;

import org.springframework.stereotype.Service;
import path.trainingapp.UniversitySystem.dto.StudentDTO;
import path.trainingapp.UniversitySystem.dto.StudentPatchDTO;
import path.trainingapp.UniversitySystem.exceptions.ResourceNotFoundException;
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

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        return studentMapper.studentToStudentDTO(studentRepository.save(student));
    }

    @Override
    public StudentDTO updateStudent(StudentPatchDTO studentPatchDTO) {
        Optional<Student> student = studentRepository.findById(studentPatchDTO.getId());
        int newAge = studentPatchDTO.getAge();
        if (student.isPresent()){
            if (newAge != 0){
                student.get().setAge(newAge);
            }
            if (studentPatchDTO.getEmail() != null){
                student.get().setEmail(studentPatchDTO.getEmail());
            }
            if (studentPatchDTO.getName() != null){
                student.get().setName(studentPatchDTO.getName());
            }

            return studentMapper.studentToStudentDTO(studentRepository.save(student.get()));
        }else{
            throw new ResourceNotFoundException("Student not found");
        }
    }

    @Override
    public String deleteStudent(Long id) {
        if (studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        }else {
            throw new ResourceNotFoundException("Student not found");
        }
    }
}
