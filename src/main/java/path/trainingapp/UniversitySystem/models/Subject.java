package path.trainingapp.UniversitySystem.models;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int credits;


    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.REMOVE)
    private Set<Course> courses = new HashSet<>();

    public Subject(Long id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getCredits();
        return result;
    }
}
