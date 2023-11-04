package tingeso.lastservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.lastservice.entity.SummaryEntity;
import tingeso.lastservice.service.SummaryService;

@RestController
@RequestMapping("/last")
public class SummaryController {

    @Autowired
    SummaryService summaryService;

    @PostMapping("/summary/")
    public ResponseEntity<SummaryEntity> createSummary(@RequestBody SummaryEntity summaryEntity){
        SummaryEntity summary = summaryService.createSummary(summaryEntity).getBody();

        if(summary == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/summary/{rut}")
    public ResponseEntity<SummaryEntity> findByRut(@PathVariable("rut") int rut){
        SummaryEntity summary = summaryService.findByRut(rut).getBody();
        if(summary == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(summary);
    }
}
