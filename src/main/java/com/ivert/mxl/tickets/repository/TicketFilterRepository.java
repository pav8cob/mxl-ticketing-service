package com.ivert.mxl.tickets.repository;

import com.ivert.mxl.tickets.domain.TicketFilter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TicketFilter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketFilterRepository extends MongoRepository<TicketFilter, String> {}
