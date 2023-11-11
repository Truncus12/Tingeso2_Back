package tingeso.feeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tingeso.feeservice.entity.PaymentEntity;
import tingeso.feeservice.service.PaymentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fee")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment/")
    public List<PaymentEntity> getAll(){
        return paymentService.getAll();
    }

    @GetMapping("/payment/{rut}")
    public PaymentEntity findByRut(@PathVariable("rut") int rut){
        return paymentService.findByRut(rut);
    }

    @GetMapping("/payment/all/{rut}")
    public List<PaymentEntity> allByRut(@PathVariable("rut") int rut){
        return paymentService.allByRut(rut);
    }

    @GetMapping("/payment/date/{rut}")
    public LocalDate dateByRut(@PathVariable("rut") int rut){
        return paymentService.dateByRut(rut);
    }
}
