package path.trainingapp.UniversitySystem.models;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int capacity;

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "course_subject",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();

    public Course(Long id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public void addStudent(Student student){
        students.add(student);
        student.getCourses().add(this);
    }
    public void addSubject(Subject subject){
        subjects.add(subject);
        subject.getCourses().add(this);
    }
}
