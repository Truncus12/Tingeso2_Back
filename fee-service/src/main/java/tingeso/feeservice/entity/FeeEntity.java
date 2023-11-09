package tingeso.feeservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public YearMonth date;
    public Float debt;
    public String state;
    public Integer rut;

}
