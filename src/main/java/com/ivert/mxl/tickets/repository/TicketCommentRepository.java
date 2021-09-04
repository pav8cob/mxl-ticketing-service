package com.ivert.mxl.tickets.repository;

import com.ivert.mxl.tickets.domain.TicketComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TicketComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketCommentRepository extends MongoRepository<TicketComment, String> {}
