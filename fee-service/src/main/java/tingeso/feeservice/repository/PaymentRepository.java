package tingeso.feeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.feeservice.entity.PaymentEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    // select last/most recent payment by rut
    @Query(value = "SELECT * FROM payment_entity WHERE payment_entity.rut = :rut ORDER BY payment_entity.date DESC LIMIT 1",
            nativeQuery = true)
    PaymentEntity findByRut(@Param("rut") Integer rut);

    // select last/most recent payment date
    @Query(value = "SELECT payment_entity.date FROM payment_entity WHERE payment_entity.rut = :rut " +
            "ORDER BY payment_entity.date DESC LIMIT 1",
            nativeQuery = true)
    LocalDate dateByRut(@Param("rut") Integer rut);

    // select all payments by rut
    @Query(value = "SELECT * FROM payment_entity WHERE payment_entity.rut = :rut", nativeQuery = true)
    List<PaymentEntity> allByRut(@Param("rut") Integer rut);
}
