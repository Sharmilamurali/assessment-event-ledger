package com.assessment.account_service.repository;

import com.assessment.account_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{

boolean existsByEventId(String eventId);

List<Transaction> findByAccountIdOrderByEventTimestampAsc(String accountId);

}