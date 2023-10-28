package tingeso.studentsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.studentsservice.entity.StudentEntity;
import tingeso.studentsservice.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping
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

    @PostMapping()
    public ResponseEntity<StudentEntity> save(@RequestBody StudentEntity student){
        StudentEntity studentNew = studentService.save(student);
        return ResponseEntity.ok(studentNew);
    }

    /*
    // CREATE STUDENT
    @GetMapping("/student/new")
    public String createStudentForm(Model model){
        return "studentEntity-create";
    }

    @PostMapping("/student/new")
    public String createStudent(@RequestParam Integer rut, String lastNames, String names, LocalDate birthdate,
                                String pastSchool, String nameSchool, Integer yearGradSchool,
                                String paymentMethod){
        studentService.saveStudent(rut, lastNames, names, birthdate, pastSchool, nameSchool, yearGradSchool,
                paymentMethod);
        return "mainPage";
    }

    // CHOOSE FEES
    @GetMapping("/chooseFees")
    public String chooseFeesForm(Model model){
        return "chooseFees";
    }
    @PostMapping("/chooseFees")
    public String chooseFees(@RequestParam Integer rut, Integer nFees, LocalDate startSemester){
        if (LocalDate.now().isEqual(startSemester.minusDays(5))){
            studentService.chooseNFees(rut,nFees);
        }
        return "mainPage";
    }
     */
}
