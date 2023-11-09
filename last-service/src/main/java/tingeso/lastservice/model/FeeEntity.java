package tingeso.lastservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeEntity {

    private Integer id;
    public YearMonth date;
    public Float debt;
    public String state;
    public Integer rut;
}
