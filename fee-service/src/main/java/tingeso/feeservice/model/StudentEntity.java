package tingeso.feeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    private String id;

    public Integer rut;
    public String lastNames;
    public String names;
    public LocalDate birthdate;
    public String pastSchool;
    public String nameSchool;
    public Integer yearGradSchool;

}