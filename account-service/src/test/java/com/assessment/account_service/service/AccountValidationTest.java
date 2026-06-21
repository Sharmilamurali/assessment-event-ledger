package com.assessment.account_service.service;

import com.assessment.account_service.entity.Transaction;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidationTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void invalidTransactionShouldFail() {

        Transaction transaction = new Transaction();

        transaction.setEventId("");

        transaction.setAccountId("");

        transaction.setAmount(null);

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertFalse(violations.isEmpty());

    }

    @Test
    void validTransactionShouldPass() {

        Transaction transaction = new Transaction();

        transaction.setEventId("EVT1");

        transaction.setAccountId("ACC1");

        transaction.setAmount(new java.math.BigDecimal("100"));

        transaction.setType("DEPOSIT");

        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertTrue(violations.isEmpty());

    }
}