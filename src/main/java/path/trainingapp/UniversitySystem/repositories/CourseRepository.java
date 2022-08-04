package path.trainingapp.UniversitySystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import path.trainingapp.UniversitySystem.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
