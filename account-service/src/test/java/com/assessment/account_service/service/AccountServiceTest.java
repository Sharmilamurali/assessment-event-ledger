package com.assessment.account_service.service;

import com.assessment.account_service.entity.Account;
import com.assessment.account_service.entity.Transaction;
import com.assessment.account_service.repository.AccountRepository;
import com.assessment.account_service.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private AccountService service;

    @BeforeEach
    void setup() {

        accountRepository = mock(AccountRepository.class);
        transactionRepository = mock(TransactionRepository.class);

        service = new AccountService(accountRepository, transactionRepository);
    }

    @Test
    void depositTransaction_shouldIncreaseBalance() {

        Account account = new Account();
        account.setAccountId("123");
        account.setBalance(BigDecimal.ZERO);

        Transaction transaction = new Transaction();

        transaction.setAccountId("123");
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setType("DEPOSIT");
        transaction.setEventId("event1");

        when(transactionRepository.existsByEventId("event1")).thenReturn(false);

        when(transactionRepository.findByAccountIdOrderByEventTimestampAsc("123")).thenReturn(List.of(transaction));

        when(accountRepository.findById("123")).thenReturn(Optional.of(account));

        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));

        service.process(transaction);

        assertEquals(BigDecimal.valueOf(100), account.getBalance());
    }

    @Test
    void duplicateTransaction_shouldNotProcessAgain() {


        Transaction transaction = new Transaction();

        transaction.setEventId("EVT001");

        when(transactionRepository.existsByEventId("EVT001")).thenReturn(true);

        service.process(transaction);

        verify(transactionRepository, never()).save(transaction);

    }

    @Test
    void getBalance_shouldReturnAccount() {

        Account account = new Account();

        account.setAccountId("ACC001");
        account.setBalance(new BigDecimal("500"));

        when(accountRepository.findById("ACC001")).thenReturn(Optional.of(account));

        Account result = service.getBalance("ACC001");

        assertEquals(new BigDecimal("500"), result.getBalance());

    }

}