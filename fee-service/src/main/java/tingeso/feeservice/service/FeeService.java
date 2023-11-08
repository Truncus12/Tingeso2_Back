package tingeso.feeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso.feeservice.entity.FeeEntity;
import tingeso.feeservice.model.StudentEntity;
import tingeso.feeservice.model.SummaryEntity;
import tingeso.feeservice.repository.FeeRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeeService {
    @Autowired
    FeeRepository feeRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PaymentService paymentService;

    public List<FeeEntity> getAll(){
        return feeRepository.findAll();
    }

    public List<FeeEntity> createFee(Integer rut, Integer nFees, LocalDate startSemester, String typePayment){

        List<FeeEntity> fees = new ArrayList<>();

        // connect to student microservice to get student
        StudentEntity student = findByRut(rut);

        float debt = 1500000f;

        if (typePayment.equalsIgnoreCase("cuotas") &&
                LocalDate.now().isEqual(startSemester.minusDays(5))){

            // discount by type of school
            switch (student.getPastSchool().toLowerCase()){
                case "municipal" -> debt = debt - (debt*0.2f);       // 20% discount
                case "subvencionado" -> debt = debt - (debt*0.1f);   // 10% discount
            }

            // discount by years since graduation
            int diffYear = Year.now().getValue() - student.getYearGradSchool();
            if (diffYear < 1) { debt = debt - (debt*0.15f); }        // 15% discount
            else if (diffYear <= 2) { debt = debt - (debt*0.08f); }  // 8% discount
            else if (diffYear <= 4) { debt = debt - (debt*0.04f); }  // 4% discount

            Float debtByFee = debt/nFees;

            if ((student.getPastSchool().equalsIgnoreCase("privado")) & (nFees <= 4)) {
                fees = createNFees(nFees, debtByFee, student.getRut());
            }
            else if ((student.getPastSchool().equalsIgnoreCase("subvencionado")) & (nFees <= 7)) {
                fees = createNFees(nFees, debtByFee, student.getRut());
            }
            else if ((student.getPastSchool().equalsIgnoreCase("municipal")) & (nFees <= 10)) {
                fees = createNFees(nFees, debtByFee, student.getRut());
            }
        }
        else if (typePayment.equalsIgnoreCase("contado")
                && LocalDate.now().isEqual(startSemester.minusDays(5))){
            FeeEntity fee = new FeeEntity();
            fee.setMonth(LocalDate.now().getMonth());
            fee.setDebt(750000f);
            fee.setState("Pendiente");
            fee.setRut(rut);
            fees.add(fee);
            feeRepository.save(fee);
        }

        saveSummaryEntity(rut, student.getNames(), debt, typePayment, nFees);

        return feeRepository.saveAll(fees);
    }

    public StudentEntity findByRut(Integer rut){
        ResponseEntity<StudentEntity> response = restTemplate.exchange(
                "http://localhost:8080/student/"+rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StudentEntity>() {}
        );
        return response.getBody();
    }

    public List<FeeEntity> createNFees(Integer nFees, Float debt, Integer rut){
        List<FeeEntity> fees = new ArrayList<>();

        for (int i = 0; i < nFees; i++){
            FeeEntity fee = new FeeEntity();
            fee.setMonth(LocalDate.now().getMonth());
            fee.setDebt(debt);
            fee.setState("Pendiente");
            fee.setRut(rut);
            fees.add(fee);
        }
        feeRepository.saveAll(fees);

        return fees;
    }

    public List<FeeEntity> byRut(Integer rut){
        List<FeeEntity> fees = feeRepository.findByRut(rut);
        fees = lateFee(fees);
        return fees;
    }

    public List<FeeEntity> byRutSummary(Integer rut){
        return feeRepository.findByRut(rut);
    }

    public List<FeeEntity> lateFee(List<FeeEntity> fees){
        int difference = 0;
        for (FeeEntity fee : fees) {
            if (!(fee.getState().equalsIgnoreCase("pagado"))){
                difference = LocalDate.now().getMonthValue() - fee.getMonth().getValue();

                if (difference > 0) {
                    fee.setState("Atrasado");

                    switch (difference){
                        case 1:
                            fee.setDebt(fee.getDebt() + (fee.getDebt()*0.03f));
                            break;
                        case 2:
                            fee.setDebt(fee.getDebt() + (fee.getDebt()*0.06f));
                            break;
                        case 3:
                            fee.setDebt(fee.getDebt() + (fee.getDebt()*0.09f));
                            break;
                    }

                    if (difference > 3){
                        fee.setDebt(fee.getDebt() + (fee.getDebt()*0.15f));
                    }
                }
            }
        }

        return feeRepository.saveAll(fees);
    }

    public FeeEntity payFee(Integer id){
        FeeEntity fee = feeRepository.findById(id).orElse(null);
        if(fee == null){
            return null;
        }
        paymentService.save(fee.getRut(), fee.getId(), fee.getDebt());
        fee.setState("Pagado");
        fee.setDebt(0f);
        return feeRepository.save(fee);
    }

    public FeeEntity updateFee(Integer id, Float debt){
        FeeEntity fee = feeRepository.findById(id).orElse(null);
        if(fee == null){
            return null;
        }
        fee.setDebt(debt);
        feeRepository.save(fee);
        return fee;
    }

    public void saveSummaryEntity(Integer rut, String name, Float totalDebt,
                                  String paymentMethod, Integer nFees){
        SummaryEntity summaryEntity = new SummaryEntity();
        summaryEntity.setRut(rut);
        summaryEntity.setName(name);
        summaryEntity.setTotalDebt(totalDebt);
        summaryEntity.setPaymentMethod(paymentMethod);
        summaryEntity.setNFees(nFees);

        System.out.println("debt: " + summaryEntity.getTotalDebt());

        restTemplate.postForObject("http://localhost:8080/last/summary/", summaryEntity, SummaryEntity.class);
    }
}