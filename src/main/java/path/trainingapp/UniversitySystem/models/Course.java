package path.trainingapp.UniversitySystem.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;
    private int capacity;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    Set<CourseRegistration> registrations = new HashSet<>();

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

    public void addCourseRegistration(CourseRegistration registration){
        registrations.add(registration);
        registration.setCourse(this);
    }
    public void addSubject(Subject subject){
        subjects.add(subject);
        subject.getCourses().add(this);
    }
}
