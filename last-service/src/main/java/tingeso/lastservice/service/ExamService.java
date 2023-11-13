package tingeso.lastservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso.lastservice.model.FeeEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SummaryService summaryService;

    // obtener todas las cuotas de un estudiante
    // obtener solo las cuotas con estado pendiente
    // calcular descuento según score
    // aplicar descuento a cada cuota
    // guardar cuotas

    public List<FeeEntity> getFeesByRut(Integer rut){
        ResponseEntity<List<FeeEntity>> responseEntity = restTemplate.exchange(
                "http://gateway-service:8080/fee/summary/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FeeEntity>>() {
                }
        );
        List<FeeEntity> fees = responseEntity.getBody();
        return fees;
    }

    public List<FeeEntity> feesPending(List<FeeEntity> fees){
        List<FeeEntity> newFees = new ArrayList<>();
        for (FeeEntity fee : fees) {
            if (fee.getState().equalsIgnoreCase("pendiente")) {
                newFees.add(fee);
            }
        }
        return newFees;
    }

    public List<FeeEntity> applyDiscount(List<FeeEntity> fees, Integer score){
        for (FeeEntity fee : fees) {
            if ((score >= 850) && (score < 900)) {
                fee.setDebt(fee.getDebt() - (fee.getDebt()*0.02f));
            }
            else if ((score >= 900) && (score < 950)) {
                fee.setDebt(fee.getDebt() - (fee.getDebt()*0.05f));
            }
            else if ((score >= 950) && (score <= 1000)) {
                fee.setDebt(fee.getDebt() - (fee.getDebt()*0.1f));
            }
        }
        return saveFees(fees);
    }

    // send new fee amount and id to fee microservice
    public List<FeeEntity> saveFees(List<FeeEntity> fees){
        for (FeeEntity fee : fees) {
            int id = fee.getId();
            float debt = fee.getDebt();
            ResponseEntity<FeeEntity> responseEntity = restTemplate.exchange(
                    "http://gateway-service:8080/fee/update?id=" + id + "&debt=" + debt,
                    HttpMethod.PUT,
                    null,
                    new ParameterizedTypeReference<FeeEntity>() {
                    }
            );
        }

        return fees;
    }

    public void addNExam(Integer rut){
        summaryService.addNExan(rut);
    }

    public void addTotalScore(Integer rut, Integer score){
        summaryService.addScore(rut, score);
    }

}
