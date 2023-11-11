package tingeso.feeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryEntity {
    private int id;

    Integer rut;            // rut
    String name;            // nombre
    Integer nExams;         // Nro. exámenes rendidos
    Integer averageScore;   // Promedio puntaje exámenes
    Float totalDebt;        // Monto total arancel a pagar
    String paymentMethod;   // Tipo Pago (Contado/Cuotas)
    Integer nFees;          // Nro. total de cuotas pactadas
    Integer nPaidFees;      // Nro. cuotas pagadas
    Float totalPaid;        // Monto total pagado
    LocalDate lastPayment;  // Fecha último pago
    Float leftDebt;         // Saldo por pagar
    Integer nLateFees;      // Nro. Cuotas con retraso
}
