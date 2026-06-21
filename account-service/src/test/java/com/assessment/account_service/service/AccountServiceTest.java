package com.assessment.account_service.service;

import com.assessment.account_service.entity.Account;
import com.assessment.account_service.entity.Transaction;
import com.assessment.account_service.repository.AccountRepository;
import com.assessment.account_service.repository.TransactionRepository;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

@Test
void depositTransaction_shouldIncreaseBalance(){

AccountRepository accountRepository=
Mockito.mock(AccountRepository.class);

TransactionRepository transactionRepository=
Mockito.mock(TransactionRepository.class);

AccountService service=
new AccountService(
accountRepository,
transactionRepository);

Transaction transaction=new Transaction();

transaction.setEventId("EVT001");
transaction.setAccountId("ACC001");
transaction.setType("DEPOSIT");
transaction.setAmount(new BigDecimal("100"));

Mockito.when(
transactionRepository.existsByEventId("EVT001")
)
.thenReturn(false);

Account account=new Account();

account.setAccountId("ACC001");
account.setBalance(BigDecimal.ZERO);

Mockito.when(
accountRepository.findById("ACC001")
)
.thenReturn(Optional.of(account));

service.process(transaction);

assertEquals(
new BigDecimal("100"),
account.getBalance()
);

Mockito.verify(accountRepository)
.save(account);

}

@Test
void duplicateTransaction_shouldNotProcessAgain(){

AccountRepository accountRepository=
Mockito.mock(AccountRepository.class);

TransactionRepository transactionRepository=
Mockito.mock(TransactionRepository.class);

AccountService service=
new AccountService(
accountRepository,
transactionRepository);

Transaction transaction=new Transaction();

transaction.setEventId("EVT001");

Mockito.when(
transactionRepository.existsByEventId("EVT001")
)
.thenReturn(true);

service.process(transaction);

Mockito.verify(
transactionRepository,
Mockito.never()
)
.save(transaction);

}

@Test
void getBalance_shouldReturnAccount(){

AccountRepository accountRepository=
Mockito.mock(AccountRepository.class);

TransactionRepository transactionRepository=
Mockito.mock(TransactionRepository.class);

AccountService service=
new AccountService(
accountRepository,
transactionRepository);

Account account=new Account();

account.setAccountId("ACC001");
account.setBalance(new BigDecimal("500"));

Mockito.when(
accountRepository.findById("ACC001")
)
.thenReturn(Optional.of(account));

Account result=
service.getBalance("ACC001");

assertEquals(
new BigDecimal("500"),
result.getBalance()
);

}

}