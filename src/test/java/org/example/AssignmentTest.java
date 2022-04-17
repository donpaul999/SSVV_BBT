package org.example;

import domain.Tema;
import org.junit.Test;
import repository.TemaXMLRepository;
import validation.TemaValidator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AssignmentTest {
    @Test
    public void addAssignmentValid(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository fileRepository = new TemaXMLRepository(temaValidator, "temaTest.xml");

        Tema tema = new Tema("1", "description", 7, 2);
        assertNotNull(fileRepository.save(tema));
    }

    @Test
    public void addAssignmentInvalidId(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository fileRepository = new TemaXMLRepository(temaValidator, "temaTest.xml");

        Tema tema = new Tema(null, "description", 7, 5);
        assertNull(fileRepository.save(tema));
    }

    @Test
    public void addAssignmentInvalidStartLineTooBig(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository fileRepository = new TemaXMLRepository(temaValidator, "temaTest.xml");

        Tema tema = new Tema("2", "description", 7, 20);
        assertNull(fileRepository.save(tema));
    }

    @Test
    public void addAssignmentInvalidEndLine(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository fileRepository = new TemaXMLRepository(temaValidator, "temaTest.xml");

        Tema tema = new Tema("2", "description", -3, 2);
        assertNull(fileRepository.save(tema));
    }

    @Test
    public void addAssignmentInvalidDescription(){
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepository fileRepository = new TemaXMLRepository(temaValidator, "temaTest.xml");

        Tema tema = new Tema("2", "", 7, 5);
        assertNull(fileRepository.save(tema));
    }

}
