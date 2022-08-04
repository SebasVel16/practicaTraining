package path.trainingapp.UniversitySystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import path.trainingapp.UniversitySystem.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
