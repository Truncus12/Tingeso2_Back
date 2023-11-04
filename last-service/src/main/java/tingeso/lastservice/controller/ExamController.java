package tingeso.lastservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tingeso.lastservice.model.FeeEntity;
import tingeso.lastservice.service.ExamService;

import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    ExamService examService;

    @PutMapping("/")
    public ResponseEntity<List<FeeEntity>> applyExam(@RequestParam Integer rut, LocalDate date, Integer score){
        List<FeeEntity> fees = examService.getFeesByRut(rut);
        if(fees.isEmpty()){
            System.out.println("No se pudo obtener las cuotas");
            return ResponseEntity.noContent().build();
        }
        fees = examService.feesPending(fees);
        if(fees.isEmpty()){
            System.out.println("No se pudo obtener las cuotas pendientes");
            return ResponseEntity.noContent().build();
        }
        fees = examService.applyDiscount(fees, score);
        if(fees.isEmpty()){
            System.out.println("No se pudo aplicar el descuento");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fees);
    }
}
