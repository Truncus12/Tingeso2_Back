package tingeso.feeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.feeservice.entity.PaymentEntity;
import tingeso.feeservice.repository.PaymentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public List<PaymentEntity> getAll(){
        return paymentRepository.findAll();
    }

    public PaymentEntity save(Integer rut, Long id, Float amount) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setRut(rut);
        paymentEntity.setDate(java.time.LocalDate.now());
        paymentEntity.setFeeId(id);
        paymentEntity.setAmount(amount);
        return paymentRepository.save(paymentEntity);
    }

    public PaymentEntity findByRut(Integer rut){
        return paymentRepository.findByRut(rut);
    }

    public LocalDate dateByRut(Integer rut){
        return paymentRepository.dateByRut(rut);
    }

    public List<PaymentEntity> allByRut(Integer rut){
        return paymentRepository.allByRut(rut);
    }
}
