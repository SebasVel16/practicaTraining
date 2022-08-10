package path.trainingapp.UniversitySystem.models.compositeKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRegistrationKey implements Serializable {

    @Column(name = "course_id")
    Long courseId;

    @Column(name = "student_id")
    Long studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseRegistrationKey that = (CourseRegistrationKey) o;

        if (getStudentId() != null ? !getStudentId().equals(that.getStudentId()) : that.getStudentId() != null)
            return false;
        return getCourseId() != null ? getCourseId().equals(that.getCourseId()) : that.getCourseId() == null;
    }

    @Override
    public int hashCode() {
        int result = getStudentId() != null ? getStudentId().hashCode() : 0;
        result = 31 * result + (getCourseId() != null ? getCourseId().hashCode() : 0);
        return result;
    }
}
