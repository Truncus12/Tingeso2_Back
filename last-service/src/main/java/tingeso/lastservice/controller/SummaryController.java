package tingeso.lastservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.lastservice.entity.SummaryEntity;
import tingeso.lastservice.service.SummaryService;

import java.util.List;

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

    @GetMapping("/summary/all")
    public ResponseEntity<List<SummaryEntity>> getAll(){
        List<SummaryEntity> summaries = summaryService.getAll();
        if(summaries.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(summaries);
    }
}
