package path.trainingapp.UniversitySystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import path.trainingapp.UniversitySystem.models.CourseRegistration;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration,Long> {
}
