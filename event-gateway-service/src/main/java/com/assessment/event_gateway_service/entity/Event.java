package com.assessment.event_gateway_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event ID is required")
    @Column(unique = true, nullable = false)
    private String eventId;

    @NotBlank(message = "Account ID is required")
    private String accountId;

    private String type;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    private String currency;

    private Instant eventTimestamp;

}