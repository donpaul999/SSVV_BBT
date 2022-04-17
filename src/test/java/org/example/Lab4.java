package org.example;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class Lab4 {
    @Test
    public void testAddStudent_validGroup() {
        Validator<Student> validator=new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(validator);
        studentRepository.save(new Student("932", "student", 932));
        Student student = studentRepository.findOne("932");
        assertEquals(student.getGrupa(), 932);
    }

    @Test
    public void integrateHomework(){
        Validator<Student> validator=new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(validator);
        studentRepository.save(new Student("932", "student", 932));
        Student student = studentRepository.findOne("932");
        Validator<Tema> validatorTema=new TemaValidator();
        TemaRepository temaRepository = new TemaRepository(validatorTema);
        temaRepository.save(new Tema("1","bla",4,2));
        Tema t = temaRepository.findOne("1");
        assertEquals(student.getGrupa(),932);
        Assertions.assertEquals(t.getDeadline(),4);
    }

    @Test
    public void integrateGrade(){
        Validator<Student> validator=new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(validator);
        Validator<Tema> validatorTema=new TemaValidator();
        TemaRepository temaRepository = new TemaRepository(validatorTema);
        NotaValidator notaValidator = new NotaValidator();
        NotaRepository notaRepository =new NotaRepository(notaValidator);
        studentRepository.save(new Student("1", "student1", 931));
        temaRepository.save(new Tema("1","blabla",3,2));
        notaRepository.save(new Nota(new Pair("adsdasdsa", "sad"),
                10,4, "asd"));
        Nota n = notaRepository.findOne(new Pair("adsdasdsa", "sad"));
    }
}
