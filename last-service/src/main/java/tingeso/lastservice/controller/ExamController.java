package tingeso.lastservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController("/exam")
public class ExamController {

    @PutMapping("/")
    public void createExam(@RequestParam Integer rut, LocalDate date, Integer score){

    }
}
