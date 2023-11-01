package tingeso.feeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso.feeservice.entity.FeeEntity;
import tingeso.feeservice.model.StudentEntity;
import tingeso.feeservice.repository.FeeRepository;

import java.util.List;

@Service
public class FeeService {
    @Autowired
    FeeRepository feeRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<FeeEntity> getAll(){
        return feeRepository.findAll();
    }

    /*
    public StudentEntity findByRut(Integer rut){
        ResponseEntity<StudentEntity> response = restTemplate.exchange(
                "http://localhost:8080/student/"+rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StudentEntity>() {}
        );
        return response.getBody();
    }
     */
}
