package com.assessment.account_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
public class Transaction {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@NotBlank(message="Event ID is required")
private String eventId;

@NotBlank(message="Account ID is required")
private String accountId;

@NotBlank(message="Transaction type is required")
private String type;

@NotNull(message="Amount is required")
@Positive(message="Amount must be greater than zero")
private BigDecimal amount;

private Instant eventTimestamp;

}