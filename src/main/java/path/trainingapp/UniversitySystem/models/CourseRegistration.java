package path.trainingapp.UniversitySystem.models;


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

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    Course course;

    private int grade;

    private int semester;

    public CourseRegistration(CourseRegistrationKey id, int grade) {
        this.id = id;
        this.grade = grade;
    }
}
