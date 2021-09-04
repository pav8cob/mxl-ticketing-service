package com.ivert.mxl.tickets.service;

import com.ivert.mxl.tickets.domain.TicketFilter;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TicketFilter}.
 */
public interface TicketFilterService {
    /**
     * Save a ticketFilter.
     *
     * @param ticketFilter the entity to save.
     * @return the persisted entity.
     */
    TicketFilter save(TicketFilter ticketFilter);

    /**
     * Partially updates a ticketFilter.
     *
     * @param ticketFilter the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TicketFilter> partialUpdate(TicketFilter ticketFilter);

    /**
     * Get all the ticketFilters.
     *
     * @return the list of entities.
     */
    List<TicketFilter> findAll();

    /**
     * Get the "id" ticketFilter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TicketFilter> findOne(String id);

    /**
     * Delete the "id" ticketFilter.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
