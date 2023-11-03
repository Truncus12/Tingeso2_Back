package tingeso.studentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.studentservice.repository.StudentRepository;
import tingeso.studentservice.entity.StudentEntity;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentEntity> getAll(){
        return studentRepository.findAll();
    }

    public StudentEntity getByRut(Integer rut){
        return studentRepository.findByRut(rut);
    }

    public StudentEntity save(StudentEntity student){
        return studentRepository.save(student);
    }
}