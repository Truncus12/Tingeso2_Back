package tingeso.lastservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeEntity {

    private Integer id;
    public Date date;
    public Float debt;
    public String state;
    public Integer rut;
}
