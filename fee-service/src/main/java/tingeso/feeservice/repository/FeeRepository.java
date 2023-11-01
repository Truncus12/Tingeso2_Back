package tingeso.feeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tingeso.feeservice.entity.FeeEntity;

@Repository
public interface FeeRepository extends JpaRepository<FeeEntity,Integer> {

}
