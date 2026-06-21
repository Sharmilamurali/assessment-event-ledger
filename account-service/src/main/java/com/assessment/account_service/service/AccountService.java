package com.assessment.account_service.service;

import com.assessment.account_service.entity.Account;
import com.assessment.account_service.entity.Transaction;
import com.assessment.account_service.repository.AccountRepository;
import com.assessment.account_service.repository.TransactionRepository;
import com.assessment.account_service.exception.AccountNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;


    public void process(Transaction transaction) {

        if (transactionRepository
                .existsByEventId(transaction.getEventId())) {

            return;

        }

        transactionRepository.save(transaction);

        List<Transaction> transactions =
                transactionRepository
                        .findByAccountIdOrderByEventTimestampAsc(
                                transaction.getAccountId());

        Account account =
                accountRepository.findById(
                                transaction.getAccountId())
                        .orElseGet(() -> {

                            Account a = new Account();

                            a.setAccountId(
                                    transaction.getAccountId());

                            a.setBalance(BigDecimal.ZERO);

                            return a;

                        });

        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction t : transactions) {

            if ("DEPOSIT".equalsIgnoreCase(
                    t.getType())) {

                balance =
                        balance.add(t.getAmount());

            } else if ("WITHDRAW".equalsIgnoreCase(
                    t.getType())) {

                balance =
                        balance.subtract(t.getAmount());

            }

        }

        account.setBalance(balance);

        accountRepository.save(account);

    }

    public Account getBalance(String id) {

        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException(
                                "Account not found: " + id
                        ));

    }

}