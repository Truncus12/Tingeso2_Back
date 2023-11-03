package tingeso.studentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.studentservice.entity.StudentEntity;
import tingeso.studentservice.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<List<StudentEntity>> getAll(){
        List<StudentEntity> students = studentService.getAll();
        if(students.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{rut}")
    public ResponseEntity<StudentEntity> getByRut(@PathVariable("rut") int rut){
        StudentEntity student = studentService.getByRut(rut);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("/")
    public ResponseEntity<StudentEntity> save(@RequestBody StudentEntity student){
        StudentEntity studentNew = studentService.save(student);
        return ResponseEntity.ok(studentNew);
    }

}