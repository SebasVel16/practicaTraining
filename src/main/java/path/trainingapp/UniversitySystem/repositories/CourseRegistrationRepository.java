package path.trainingapp.UniversitySystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import path.trainingapp.UniversitySystem.models.CourseRegistration;
import path.trainingapp.UniversitySystem.models.compositeKey.CourseRegistrationKey;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration,Long> {

    List<CourseRegistration> findTop5BySemesterOrderByGradeDesc(int semester);

    Optional<CourseRegistration> findById(CourseRegistrationKey courseRegistrationKey);

}
