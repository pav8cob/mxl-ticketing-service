package com.ivert.mxl.tickets.service.impl;

import com.ivert.mxl.tickets.domain.TicketComment;
import com.ivert.mxl.tickets.repository.TicketCommentRepository;
import com.ivert.mxl.tickets.service.TicketCommentService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link TicketComment}.
 */
@Service
public class TicketCommentServiceImpl implements TicketCommentService {

    private final Logger log = LoggerFactory.getLogger(TicketCommentServiceImpl.class);

    private final TicketCommentRepository ticketCommentRepository;

    public TicketCommentServiceImpl(TicketCommentRepository ticketCommentRepository) {
        this.ticketCommentRepository = ticketCommentRepository;
    }

    @Override
    public TicketComment save(TicketComment ticketComment) {
        log.debug("Request to save TicketComment : {}", ticketComment);
        return ticketCommentRepository.save(ticketComment);
    }

    @Override
    public Optional<TicketComment> partialUpdate(TicketComment ticketComment) {
        log.debug("Request to partially update TicketComment : {}", ticketComment);

        return ticketCommentRepository
            .findById(ticketComment.getId())
            .map(
                existingTicketComment -> {
                    if (ticketComment.getBody() != null) {
                        existingTicketComment.setBody(ticketComment.getBody());
                    }
                    if (ticketComment.getIsPublic() != null) {
                        existingTicketComment.setIsPublic(ticketComment.getIsPublic());
                    }
                    if (ticketComment.getAuthorId() != null) {
                        existingTicketComment.setAuthorId(ticketComment.getAuthorId());
                    }
                    if (ticketComment.getReplyto() != null) {
                        existingTicketComment.setReplyto(ticketComment.getReplyto());
                    }
                    if (ticketComment.getAttachments() != null) {
                        existingTicketComment.setAttachments(ticketComment.getAttachments());
                    }
                    if (ticketComment.getUserId() != null) {
                        existingTicketComment.setUserId(ticketComment.getUserId());
                    }
                    if (ticketComment.getCreateDate() != null) {
                        existingTicketComment.setCreateDate(ticketComment.getCreateDate());
                    }
                    if (ticketComment.getCreatedBy() != null) {
                        existingTicketComment.setCreatedBy(ticketComment.getCreatedBy());
                    }
                    if (ticketComment.getModifiedDate() != null) {
                        existingTicketComment.setModifiedDate(ticketComment.getModifiedDate());
                    }
                    if (ticketComment.getModifiedBy() != null) {
                        existingTicketComment.setModifiedBy(ticketComment.getModifiedBy());
                    }

                    return existingTicketComment;
                }
            )
            .map(ticketCommentRepository::save);
    }

    @Override
    public Page<TicketComment> findAll(Pageable pageable) {
        log.debug("Request to get all TicketComments");
        return ticketCommentRepository.findAll(pageable);
    }

    @Override
    public Optional<TicketComment> findOne(String id) {
        log.debug("Request to get TicketComment : {}", id);
        return ticketCommentRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete TicketComment : {}", id);
        ticketCommentRepository.deleteById(id);
    }
}
