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
    Integer nExams;         // Nro. exámenes rendidos
    Integer averageScore;   // Promedio puntaje exámenes
    Float totalDebt;        // Monto total arancel a pagar
    String paymentMehotd;   // Tipo Pago (Contado/Cuotas)
    Integer nFees;          // Nro. total de cuotas pactadas
    Integer nPaidFees;      // Nro. cuotas pagadas
    Float totalPaid;        // Monto total pagado
    LocalDate lastPayment;  // Fecha último pago
    Float leftDebt;         // Saldo por pagar
    Integer nLateFees;      // Nro. Cuotas con retraso
}

/*
    Idea:
    Cada vez que se crea un student se crea un SummaryEntity
        Se asignan los datos que se pueden crear en el momento
        Rut - name - totalDebt - paymentMethod - nFees
    Cada vez que se lee un exam aumenta nExman de SummaryEntity
        Con findByRut de SummaryEntity
    ¿PROMEDIO PUNTAJE?
        ¿Se guardan notas en un arreglo y un valor siempre es el promedio?
        ¿Cuando se hace esto? ¿1 vez al año?
    Cada vez que se piden los Summary se calculan:
        nPaidFees - totalPaid - leftDebt - nLateFees
    ¿Cada vez que se paga se guarda la fecha de último pago?

    TO-DO:
    - Implementar un findByRut de SummaryEntity
    - Crear student con crear Summary
    - Leer examen con aumentar nExam
    - Controller pedir Summaries
    - Calcular nPaidFees
    - Calcular totalPaid
    - Calcular leftDebt
    - Calcular nLateFees
 */