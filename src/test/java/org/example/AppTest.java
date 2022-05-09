package org.example;

import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AppTest
{
    Service service;

    @Before
    public void before() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator  = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator();

        StudentXMLRepository studentXMLRepository = new StudentXMLRepository(studentValidator, "test_files/studenti.xml");
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "test_files/teme.xml");
        NotaXMLRepository notaXMLRepository = new NotaXMLRepository(notaValidator, "test_files/note.xml");

        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void testAddAssignmentIntegration() {
        int result = service.saveStudent("6", "Mike", 937);
        assertEquals(1, result);

        int result1 = service.saveTema("6", "Assignment6", 12, 10);
        assertEquals(1, result1);

        service.deleteStudent("6");
        service.deleteTema("6");
    }

    @Test
    public void testAddGradeIntegration() {
        int result = service.saveStudent("9", "Jordan", 937);
        assertEquals(1, result);

        int result1 = service.saveTema("9", "Assignment7", 12, 10);
        assertEquals(1, result1);

        int result2 = service.saveNota("9", "9", 9, 11, "Good Job!");
        assertEquals(1, result2);

        service.deleteStudent("9");
        service.deleteTema("9");
    }


}
