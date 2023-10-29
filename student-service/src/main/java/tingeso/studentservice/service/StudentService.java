package tingeso.studentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.studentservice.repository.StudentRepository;
import tingeso.studentservice.entity.StudentEntity;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentEntity> getAll(){
        return studentRepository.findAll();
    }

    public StudentEntity getByRut(Integer rut){
        return studentRepository.findByRut(rut);
    }

    public StudentEntity save(StudentEntity student){
        return studentRepository.save(student);
    }

    /*
    public StudentEntity saveStudent(Integer rut, String lastNames, String names, LocalDate birthdate,
                                     String pastSchool, String nameSchool, Integer yearGradSchool,
                                     String paymentMethod){
        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setRut(rut);
        studentEntity.setLastNames(lastNames);
        studentEntity.setNames(names);
        studentEntity.setBirthdate(birthdate);
        studentEntity.setPastSchool(pastSchool);
        studentEntity.setNameSchool(nameSchool);
        studentEntity.setYearGradSchool(yearGradSchool);

        PaymentEntity paymentEntity = new PaymentEntity();

        Float value = discountByStudent(studentEntity, paymentEntity, paymentMethod);
        paymentEntity.setValue(value);
        studentEntity.setPaymentEntity(paymentEntity);

        return studentRepository.save(studentEntity);
    }

    public Float discountByStudent(StudentEntity studentEntity, PaymentEntity paymentEntity, String paymentMethod){

        Float value = paymentEntity.getValue();

        if (paymentMethod.equalsIgnoreCase("contado")){
            value = value - (value*0.5f); // 50% discount

        } else {

            // discount by type of school
            switch (studentEntity.getPastSchool().toLowerCase()){
                case "municipal" -> value = value - (value*0.2f);       // 20% discount
                case "subvencionado" -> value = value - (value*0.1f);   // 10% discount
            }

            // discount by years since graduation
            int diffYear = Year.now().getValue() - studentEntity.getYearGradSchool();
            if (diffYear < 1) { value = value - (value*0.15f); }        // 15% discount
            else if (diffYear <= 2) { value = value - (value*0.08f); }  // 8% discount
            else if (diffYear <= 4) { value = value - (value*0.04f); }  // 4% discount
        }

        return value;
    }

    public void chooseNFees(Integer rut, Integer nFees){
        StudentEntity studentEntity = studentRepository.findByRut(rut);

        Float valuePerFee = studentEntity.getPaymentEntity().getValue() / nFees;

        if ((studentEntity.getPastSchool().equalsIgnoreCase("privado")) & (nFees <= 4)) {
            creatNFees(studentEntity, valuePerFee, nFees);
        }
        else if ((studentEntity.getPastSchool().equalsIgnoreCase("subvencionado")) & (nFees <= 7)) {
            creatNFees(studentEntity, valuePerFee, nFees);
        }
        else if ((studentEntity.getPastSchool().equalsIgnoreCase("municipal")) & (nFees <= 10)) {
            creatNFees(studentEntity, valuePerFee, nFees);
        }
    }
    public void creatNFees(StudentEntity studentEntity, Float value, Integer nFees){

        for (int i = 0; i < nFees; i++) {
            FeeEntity feeEntity = new FeeEntity();

            feeEntity.setDebt(value);
            feeEntity.setState("pendiente");
            feeEntity.setMonth(LocalDate.now().getMonth());

            studentEntity.getPaymentEntity().getFeeEntities().add(feeEntity);
            studentRepository.save(studentEntity);
        }
    }
     */
}