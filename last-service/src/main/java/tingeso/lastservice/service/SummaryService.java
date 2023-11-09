package tingeso.lastservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso.lastservice.entity.SummaryEntity;
import tingeso.lastservice.model.FeeEntity;
import tingeso.lastservice.model.PaymentEntity;
import tingeso.lastservice.repository.SummaryRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SummaryService {

    @Autowired
    SummaryRepository summaryRepository;

    @Autowired
    RestTemplate restTemplate;

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
        summary.setTotalDebt(summaryEntity.getTotalDebt());
        summary.setPaymentMethod(summaryEntity.getPaymentMethod());
        summary.setNFees(summaryEntity.getNFees());
        summaryRepository.save(summary);
        return ResponseEntity.ok(summary);
    }

    public SummaryEntity addNExan(Integer rut){
        SummaryEntity summary = summaryRepository.findByRut(rut);
        summary.setNExams(summary.getNExams()+1);
        summaryRepository.save(summary);
        return summary;
    }

    public List<FeeEntity> getFees(Integer rut){
        ResponseEntity<List<FeeEntity>> response = restTemplate.exchange(
                "http://localhost:8080/fee/summary/"+rut,
                org.springframework.http.HttpMethod.GET,
                null,
                new org.springframework.core.ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public Integer getNPaidFees(List<FeeEntity> fees){

        List<FeeEntity> paidFees = new ArrayList<>();

        for (FeeEntity fee : fees) {
            if (fee.getState().equalsIgnoreCase("pagado")) {
                paidFees.add(fee);
            }
        }

        return Objects.requireNonNull(paidFees).size();
    }

    public Integer getNLateFees(List<FeeEntity> fees){
        Integer nLateFees = 0;

        for (FeeEntity fee : fees) {
            if (fee.getState().equalsIgnoreCase("atrasado")) {
                nLateFees++;
            }
        }

        return nLateFees;
    }

    public Float leftDebt(List<FeeEntity> fees){
        Float leftDebt = 0f;

        for (FeeEntity fee : fees) {
            if (!(fee.getState().equalsIgnoreCase("pagado"))) {
                leftDebt += fee.getDebt();
            }
        }

        return leftDebt;
    }

    public Float averageScore(SummaryEntity summary){
        if (summary.getNExams() == 0){
            return 0f;
        }
        return summary.getTotalScore()/summary.getNExams();
    }

    public void addScore(Integer rut, Integer score){
        SummaryEntity summary = summaryRepository.findByRut(rut);
        summary.setTotalScore(summary.getTotalScore()+score);
        summaryRepository.save(summary);
    }


    public Float totalPaid(Integer rut){
        Float totalPaid = 0f;

        // connection to payment
        ResponseEntity<List<PaymentEntity>> response = restTemplate.exchange(
                "http://localhost:8080/fee/payment/all/"+rut,
                org.springframework.http.HttpMethod.GET,
                null,
                new org.springframework.core.ParameterizedTypeReference<>() {}
        );

        List<PaymentEntity> payments = response.getBody();
        assert payments != null;
        for (PaymentEntity payment : payments) {
            totalPaid += payment.getAmount();
        }

        return totalPaid;
    }

    public LocalDate lastPayment(Integer rut){
        // connection to payment
        ResponseEntity<LocalDate> response = restTemplate.exchange(
                "http://localhost:8080/fee/payment/date/"+rut,
                org.springframework.http.HttpMethod.GET,
                null,
                new org.springframework.core.ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public List<SummaryEntity> getAll(){
        List<SummaryEntity> summaries = summaryRepository.findAll();
        List<FeeEntity> fees;
        for (SummaryEntity summary : summaries) {
            fees = getFees(summary.getRut());
            summary.setAverageScore(averageScore(summary));
            summary.setTotalPaid(totalPaid(summary.getRut()));
            summary.setLastPayment(lastPayment(summary.getRut()));
            summary.setLeftDebt(leftDebt(fees));
            summary.setNPaidFees(getNPaidFees(fees));
            summary.setNLateFees(getNLateFees(fees));
        }
        return summaries;
    }
}
