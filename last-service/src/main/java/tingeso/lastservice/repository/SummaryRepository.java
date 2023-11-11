package tingeso.lastservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.lastservice.entity.SummaryEntity;

@Repository
public interface SummaryRepository extends JpaRepository<SummaryEntity, Integer> {

    @Query(value = "SELECT * FROM summary_entity WHERE summary_entity.rut = :rut", nativeQuery = true)
    SummaryEntity findByRut(@Param("rut") Integer rut);
}
