package tingeso.lastservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tingeso.lastservice.entity.SummaryEntity;
import tingeso.lastservice.repository.SummaryRepository;

@Service
public class SummaryService {

    @Autowired
    SummaryRepository summaryRepository;

    public ResponseEntity<SummaryEntity> findByRut(int rut){
        SummaryEntity summary = summaryRepository.findByRut(rut);
        if(summary == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(summary);
    }

    public ResponseEntity<SummaryEntity> createSummary(SummaryEntity summaryEntity){
        SummaryEntity summary = new SummaryEntity();
        summary.setRut(summaryEntity.getRut());
        summary.setName(summaryEntity.getName());
        summary.setTotalDebt(summary.getTotalDebt());
        summary.setPaymentMethod(summaryEntity.getPaymentMethod());
        summary.setNFees(summaryEntity.getNFees());
        summaryRepository.save(summary);
        return ResponseEntity.ok(summary);
    }
}
