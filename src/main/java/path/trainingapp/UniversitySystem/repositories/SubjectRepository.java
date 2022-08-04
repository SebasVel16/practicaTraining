package path.trainingapp.UniversitySystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import path.trainingapp.UniversitySystem.models.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
