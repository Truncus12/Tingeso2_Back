package tingeso.studentsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer rut;
    public String lastNames;
    public String names;
    public LocalDate birthdate;
    public String pastSchool;
    public String nameSchool;
    public Integer yearGradSchool;

}
