package tingeso.lastservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso.lastservice.model.FeeEntity;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    RestTemplate restTemplate;

    // obtener todas las cuotas de un estudiante
    // obtener solo las cuotas con estado pendiente
    // calcular descuento según score
    // aplicar descuento a cada cuota
    // guardar cuotas

    public List<FeeEntity> getFeesByRut(Integer rut){
        ResponseEntity<List<FeeEntity>> responseEntity = restTemplate.exchange(
                "http://localhost:8081/fee/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FeeEntity>>() {
                }
        );
        List<FeeEntity> fees = responseEntity.getBody();
        return fees;
    }
}
