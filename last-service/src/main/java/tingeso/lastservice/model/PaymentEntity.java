package tingeso.lastservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

        private Long id;
        public LocalDate date;
        public Integer rut;
        public Long feeId;
        public Float amount;
}
