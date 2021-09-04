package com.ivert.mxl.tickets.service;

import com.ivert.mxl.tickets.domain.TicketComment;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link TicketComment}.
 */
public interface TicketCommentService {
    /**
     * Save a ticketComment.
     *
     * @param ticketComment the entity to save.
     * @return the persisted entity.
     */
    TicketComment save(TicketComment ticketComment);

    /**
     * Partially updates a ticketComment.
     *
     * @param ticketComment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TicketComment> partialUpdate(TicketComment ticketComment);

    /**
     * Get all the ticketComments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TicketComment> findAll(Pageable pageable);

    /**
     * Get the "id" ticketComment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TicketComment> findOne(String id);

    /**
     * Delete the "id" ticketComment.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
