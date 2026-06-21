package com.assessment.account_service.controller;

import com.assessment.account_service.entity.Account;
import com.assessment.account_service.entity.Transaction;
import com.assessment.account_service.service.AccountService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/accounts/{id}/transactions")
    public String transaction(
            @PathVariable String id,
            @Valid @RequestBody Transaction transaction) {

        transaction.setAccountId(id);

        service.process(transaction);

        return "SUCCESS";

    }

    @GetMapping("/accounts/{id}/balance")
    public Account balance(@PathVariable String id) {

        return service.getBalance(id);

    }

    @GetMapping("/health")
    public String health() {

        return "UP";

    }

}