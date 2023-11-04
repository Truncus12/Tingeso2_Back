package tingeso.lastservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeEntity {

    private Integer id;
    public Month month;
    public Float debt;
    public String state;
    public Integer rut;
}
