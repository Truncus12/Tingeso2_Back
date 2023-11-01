package tingeso.feeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tingeso.feeservice.entity.FeeEntity;
import tingeso.feeservice.service.FeeService;

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
}
