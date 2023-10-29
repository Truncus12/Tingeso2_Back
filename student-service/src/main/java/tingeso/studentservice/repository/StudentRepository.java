package tingeso.studentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.studentservice.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Query(value = "SELECT * FROM public.student WHERE student.rut = :rut", nativeQuery = true)
    StudentEntity findByRut(@Param("rut") Integer rut);

}