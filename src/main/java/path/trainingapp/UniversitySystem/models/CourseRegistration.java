package path.trainingapp.UniversitySystem.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import path.trainingapp.UniversitySystem.models.compositeKey.CourseRegistrationKey;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {

    @EmbeddedId
    CourseRegistrationKey id;

    @JsonBackReference
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    @JsonBackReference
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    private double grade;

    private int semester;

    public CourseRegistration(CourseRegistrationKey id, int grade) {
        this.id = id;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseRegistration that = (CourseRegistration) o;

        if (Double.compare(that.getGrade(), getGrade()) != 0) return false;
        if (getSemester() != that.getSemester()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getStudent() != null ? !getStudent().equals(that.getStudent()) : that.getStudent() != null) return false;
        return getCourse() != null ? getCourse().equals(that.getCourse()) : that.getCourse() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        temp = Double.doubleToLongBits(getGrade());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getSemester();
        return result;
    }
}
