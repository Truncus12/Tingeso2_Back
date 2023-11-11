package tingeso.feeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.feeservice.entity.FeeEntity;
import tingeso.feeservice.service.FeeService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController {

    @Autowired
    FeeService feeService;


    @GetMapping("/")
    public ResponseEntity<List<FeeEntity>> getAll(){
        List<FeeEntity> fees = feeService.getAll();
        if(fees.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fees);
    }

    @PostMapping("/")
    public ResponseEntity<List<FeeEntity>> createFee(@RequestParam Integer rut, Integer nFees,
                                                     LocalDate startSemester, String typePayment){

        List<FeeEntity> fees = feeService.createFee(rut, nFees, startSemester, typePayment);
        if(fees.isEmpty()){
            System.out.println("No se pudo crear la cuota");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fees);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<List<FeeEntity>> byRut(@PathVariable("rut") Integer rut){
        List<FeeEntity> fees = feeService.byRut(rut);
        if(fees.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fees);
    }

    @GetMapping("/summary/{rut}")
    public ResponseEntity<List<FeeEntity>> byRutSummary(@PathVariable("rut") int rut){
        List<FeeEntity> fees = feeService.byRutSummary(rut);
        if(fees.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeEntity> payFee(@PathVariable("id") int id){
        FeeEntity feeUpdated = feeService.payFee(id);
        if(feeUpdated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feeUpdated);
    }

    @PutMapping("/update")
    public ResponseEntity<FeeEntity> updateFee(@RequestParam Integer id, Float debt){
        FeeEntity feeUpdated = feeService.updateFee(id,debt);
        if(feeUpdated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feeUpdated);
    }
}