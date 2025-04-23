package edu.comillas.icai.gitt.pat.spring.p5.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO#7
 * Añade 2 tests unitarios adicionales que validen diferentes casos
 * (no variaciones del mismo caso) de registro con datos inválidos
 */

class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void testValidRequest() {
        // Given ...
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaaaA1");
        // When ...
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        // Then ...
        assertTrue(violations.isEmpty());
    }
    @Test
    public void testInvalidEmail(){
        RegisterRequest registro = new RegisterRequest(
                "nombre", "correo-no-valido",Role.USER, "aaaaaaaaA1");
        Set<ConstraintViolation<RegisterRequest>> violations=
                validator.validate(registro);
        assertFalse(violations.isEmpty());

    }

    @Test
    public void testPasswordTooShort(){
        RegisterRequest registro =new RegisterRequest(
                "Nombre","nombre@gmail.com",Role.USER,"aaA1");
        Set<ConstraintViolation<RegisterRequest>> violations=
                validator.validate(registro);
        assertFalse(violations.isEmpty());
    }



}