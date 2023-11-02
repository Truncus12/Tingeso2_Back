package tingeso.feeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.feeservice.entity.FeeEntity;

import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<FeeEntity,Integer> {

    @Query(value = "SELECT * FROM fee_entity WHERE fee_entity.rut = :rut", nativeQuery = true)
    List<FeeEntity> findByRut(@Param("rut") Integer rut);
}
