package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;

public class StudentTest {
    protected Validator<Student> studentValidator;
    protected Validator<Tema> temaValidator;
    protected Validator<Nota> notaValidator;

    protected StudentXMLRepository fileRepository1;
    protected TemaXMLRepository fileRepository2;
    protected NotaXMLRepository fileRepository3;

    protected Service service;

    @Before
    public void setUp(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();
        fileRepository1 = new StudentXMLRepository(studentValidator, "test_files/studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "test_files/teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "test_files/note.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testAddStudentEC(){
        //intervals:
        // [-inf, 930] BAD
        // [111, 937] GOOD
        // [938, inf] BAD
        assertEquals(0, this.service.saveStudent("998", "Test Student", 50));
        assertEquals(0, this.service.saveStudent("997", "Test Student", 1000));
        assertEquals(1, this.service.saveStudent("999", "Test Student", 932));

        this.service.deleteStudent("999");
    }

    @Test
    public void testAddStudentBVA(){
        //boundaries: 111 and 937
        //examples: 108 109 110
        //          936 937 938
        assertEquals(0, this.service.saveStudent("998", "Test Student", 110));
        assertEquals(1, this.service.saveStudent("997", "Test Student", 111));
        assertEquals(1, this.service.saveStudent("999", "Test Student", 112));

        assertEquals(1, this.service.saveStudent("1000", "Test Student", 936));
        assertEquals(1, this.service.saveStudent("1001", "Test Student", 937));
        assertEquals(0, this.service.saveStudent("1002", "Test Student", 938));
        this.service.deleteStudent("999");
        this.service.deleteStudent("997");
        this.service.deleteStudent("1000");
        this.service.deleteStudent("1001");
    }

}
