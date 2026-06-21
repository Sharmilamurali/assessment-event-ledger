package com.assessment.event_gateway_service.repository;

import com.assessment.event_gateway_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long>{

boolean existsByEventId(String eventId);

Event findByEventId(String eventId);

List<Event> findByAccountIdOrderByEventTimestampAsc(String accountId);

}