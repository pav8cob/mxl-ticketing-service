package com.ivert.mxl.tickets.service.impl;

import com.ivert.mxl.tickets.domain.Ticket;
import com.ivert.mxl.tickets.repository.TicketRepository;
import com.ivert.mxl.tickets.service.TicketService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Ticket}.
 */
@Service
public class TicketServiceImpl implements TicketService {

    private final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        log.debug("Request to save Ticket : {}", ticket);
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> partialUpdate(Ticket ticket) {
        log.debug("Request to partially update Ticket : {}", ticket);

        return ticketRepository
            .findById(ticket.getId())
            .map(
                existingTicket -> {
                    if (ticket.getSourceId() != null) {
                        existingTicket.setSourceId(ticket.getSourceId());
                    }
                    if (ticket.getSourceType() != null) {
                        existingTicket.setSourceType(ticket.getSourceType());
                    }
                    if (ticket.getType() != null) {
                        existingTicket.setType(ticket.getType());
                    }
                    if (ticket.getSubject() != null) {
                        existingTicket.setSubject(ticket.getSubject());
                    }
                    if (ticket.getDescription() != null) {
                        existingTicket.setDescription(ticket.getDescription());
                    }
                    if (ticket.getPriority() != null) {
                        existingTicket.setPriority(ticket.getPriority());
                    }
                    if (ticket.getStatus() != null) {
                        existingTicket.setStatus(ticket.getStatus());
                    }
                    if (ticket.getOwnerId() != null) {
                        existingTicket.setOwnerId(ticket.getOwnerId());
                    }
                    if (ticket.getAssignedId() != null) {
                        existingTicket.setAssignedId(ticket.getAssignedId());
                    }
                    if (ticket.getAssignedGroupId() != null) {
                        existingTicket.setAssignedGroupId(ticket.getAssignedGroupId());
                    }
                    if (ticket.getTags() != null) {
                        existingTicket.setTags(ticket.getTags());
                    }
                    if (ticket.getIsPrivate() != null) {
                        existingTicket.setIsPrivate(ticket.getIsPrivate());
                    }
                    if (ticket.getDueDate() != null) {
                        existingTicket.setDueDate(ticket.getDueDate());
                    }
                    if (ticket.getFollowto() != null) {
                        existingTicket.setFollowto(ticket.getFollowto());
                    }
                    if (ticket.getNotify() != null) {
                        existingTicket.setNotify(ticket.getNotify());
                    }
                    if (ticket.getNotificationTime() != null) {
                        existingTicket.setNotificationTime(ticket.getNotificationTime());
                    }
                    if (ticket.getUserId() != null) {
                        existingTicket.setUserId(ticket.getUserId());
                    }
                    if (ticket.getCreateDate() != null) {
                        existingTicket.setCreateDate(ticket.getCreateDate());
                    }
                    if (ticket.getCreatedBy() != null) {
                        existingTicket.setCreatedBy(ticket.getCreatedBy());
                    }
                    if (ticket.getModifiedDate() != null) {
                        existingTicket.setModifiedDate(ticket.getModifiedDate());
                    }
                    if (ticket.getModifiedBy() != null) {
                        existingTicket.setModifiedBy(ticket.getModifiedBy());
                    }

                    return existingTicket;
                }
            )
            .map(ticketRepository::save);
    }

    @Override
    public Page<Ticket> findAll(Pageable pageable) {
        log.debug("Request to get all Tickets");
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Optional<Ticket> findOne(String id) {
        log.debug("Request to get Ticket : {}", id);
        return ticketRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Ticket : {}", id);
        ticketRepository.deleteById(id);
    }
}
