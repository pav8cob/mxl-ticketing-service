package com.ivert.mxl.tickets.service.impl;

import com.ivert.mxl.tickets.domain.TicketFilter;
import com.ivert.mxl.tickets.repository.TicketFilterRepository;
import com.ivert.mxl.tickets.service.TicketFilterService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link TicketFilter}.
 */
@Service
public class TicketFilterServiceImpl implements TicketFilterService {

    private final Logger log = LoggerFactory.getLogger(TicketFilterServiceImpl.class);

    private final TicketFilterRepository ticketFilterRepository;

    public TicketFilterServiceImpl(TicketFilterRepository ticketFilterRepository) {
        this.ticketFilterRepository = ticketFilterRepository;
    }

    @Override
    public TicketFilter save(TicketFilter ticketFilter) {
        log.debug("Request to save TicketFilter : {}", ticketFilter);
        return ticketFilterRepository.save(ticketFilter);
    }

    @Override
    public Optional<TicketFilter> partialUpdate(TicketFilter ticketFilter) {
        log.debug("Request to partially update TicketFilter : {}", ticketFilter);

        return ticketFilterRepository
            .findById(ticketFilter.getId())
            .map(
                existingTicketFilter -> {
                    if (ticketFilter.getFilterName() != null) {
                        existingTicketFilter.setFilterName(ticketFilter.getFilterName());
                    }
                    if (ticketFilter.getFilter() != null) {
                        existingTicketFilter.setFilter(ticketFilter.getFilter());
                    }
                    if (ticketFilter.getUserId() != null) {
                        existingTicketFilter.setUserId(ticketFilter.getUserId());
                    }
                    if (ticketFilter.getCreateDate() != null) {
                        existingTicketFilter.setCreateDate(ticketFilter.getCreateDate());
                    }
                    if (ticketFilter.getCreatedBy() != null) {
                        existingTicketFilter.setCreatedBy(ticketFilter.getCreatedBy());
                    }
                    if (ticketFilter.getModifiedDate() != null) {
                        existingTicketFilter.setModifiedDate(ticketFilter.getModifiedDate());
                    }
                    if (ticketFilter.getModifiedBy() != null) {
                        existingTicketFilter.setModifiedBy(ticketFilter.getModifiedBy());
                    }

                    return existingTicketFilter;
                }
            )
            .map(ticketFilterRepository::save);
    }

    @Override
    public List<TicketFilter> findAll() {
        log.debug("Request to get all TicketFilters");
        return ticketFilterRepository.findAll();
    }

    @Override
    public Optional<TicketFilter> findOne(String id) {
        log.debug("Request to get TicketFilter : {}", id);
        return ticketFilterRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete TicketFilter : {}", id);
        ticketFilterRepository.deleteById(id);
    }
}
