package tingeso.lastservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    Integer rut;            // rut
    String name;            // nombre
    Integer nExams = 0;     // Nro. exámenes rendidos
    Float averageScore;     // Promedio puntaje exámenes
    Float totalDebt;        // Monto total arancel a pagar
    String paymentMethod;   // Tipo Pago (Contado/Cuotas)
    Integer nFees;          // Nro. total de cuotas pactadas
    Integer nPaidFees;      // Nro. cuotas pagadas
    Float totalPaid;        // Monto total pagado
    LocalDate lastPayment;  // Fecha último pago
    Float leftDebt;         // Saldo por pagar
    Integer nLateFees;      // Nro. Cuotas con retraso

    private Float totalScore = 0f;
}

/*
    Idea:
    (X) Cada vez que se crea un fee se crea un SummaryEntity
        Se asignan los datos que se pueden crear en el momento
        Rut - name - totalDebt - paymentMethod - nFees
    (X) Cada vez que se lee un exam aumenta nExman de SummaryEntity
        Con findByRut de SummaryEntity
    (X) Promedio score
        Se tiene siempre el total de score y se divide en nExams
        cuando se pide el summary
    (X) Cada vez que se piden los Summary se calculan:
        nPaidFees - totalPaid - leftDebt - nLateFees
    ¿Cada vez que se paga se guarda la fecha de último pago?

    TO-DO:
    X Implementar un findByRut de SummaryEntity
    X Crear fee con crear Summary
    X Leer examen con aumentar nExam
    X Controller pedir Summaries
    X Calcular nPaidFees
    X Calcular nLateFees
    X Calcular leftDebt
    - Calcular totalPaid
 */