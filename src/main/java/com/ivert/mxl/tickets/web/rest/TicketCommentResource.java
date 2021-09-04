package com.ivert.mxl.tickets.web.rest;

import com.ivert.mxl.tickets.domain.TicketComment;
import com.ivert.mxl.tickets.repository.TicketCommentRepository;
import com.ivert.mxl.tickets.service.TicketCommentService;
import com.ivert.mxl.tickets.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ivert.mxl.tickets.domain.TicketComment}.
 */
@RestController
@RequestMapping("/api")
public class TicketCommentResource {

    private final Logger log = LoggerFactory.getLogger(TicketCommentResource.class);

    private static final String ENTITY_NAME = "mxlTicketingTicketComment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TicketCommentService ticketCommentService;

    private final TicketCommentRepository ticketCommentRepository;

    public TicketCommentResource(TicketCommentService ticketCommentService, TicketCommentRepository ticketCommentRepository) {
        this.ticketCommentService = ticketCommentService;
        this.ticketCommentRepository = ticketCommentRepository;
    }

    /**
     * {@code POST  /ticket-comments} : Create a new ticketComment.
     *
     * @param ticketComment the ticketComment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ticketComment, or with status {@code 400 (Bad Request)} if the ticketComment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ticket-comments")
    public ResponseEntity<TicketComment> createTicketComment(@Valid @RequestBody TicketComment ticketComment) throws URISyntaxException {
        log.debug("REST request to save TicketComment : {}", ticketComment);
        if (ticketComment.getId() != null) {
            throw new BadRequestAlertException("A new ticketComment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TicketComment result = ticketCommentService.save(ticketComment);
        return ResponseEntity
            .created(new URI("/api/ticket-comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ticket-comments/:id} : Updates an existing ticketComment.
     *
     * @param id the id of the ticketComment to save.
     * @param ticketComment the ticketComment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketComment,
     * or with status {@code 400 (Bad Request)} if the ticketComment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ticketComment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ticket-comments/{id}")
    public ResponseEntity<TicketComment> updateTicketComment(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody TicketComment ticketComment
    ) throws URISyntaxException {
        log.debug("REST request to update TicketComment : {}, {}", id, ticketComment);
        if (ticketComment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ticketComment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ticketCommentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TicketComment result = ticketCommentService.save(ticketComment);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ticketComment.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /ticket-comments/:id} : Partial updates given fields of an existing ticketComment, field will ignore if it is null
     *
     * @param id the id of the ticketComment to save.
     * @param ticketComment the ticketComment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketComment,
     * or with status {@code 400 (Bad Request)} if the ticketComment is not valid,
     * or with status {@code 404 (Not Found)} if the ticketComment is not found,
     * or with status {@code 500 (Internal Server Error)} if the ticketComment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ticket-comments/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TicketComment> partialUpdateTicketComment(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody TicketComment ticketComment
    ) throws URISyntaxException {
        log.debug("REST request to partial update TicketComment partially : {}, {}", id, ticketComment);
        if (ticketComment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ticketComment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ticketCommentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TicketComment> result = ticketCommentService.partialUpdate(ticketComment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ticketComment.getId())
        );
    }

    /**
     * {@code GET  /ticket-comments} : get all the ticketComments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ticketComments in body.
     */
    @GetMapping("/ticket-comments")
    public ResponseEntity<List<TicketComment>> getAllTicketComments(Pageable pageable) {
        log.debug("REST request to get a page of TicketComments");
        Page<TicketComment> page = ticketCommentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ticket-comments/:id} : get the "id" ticketComment.
     *
     * @param id the id of the ticketComment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ticketComment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ticket-comments/{id}")
    public ResponseEntity<TicketComment> getTicketComment(@PathVariable String id) {
        log.debug("REST request to get TicketComment : {}", id);
        Optional<TicketComment> ticketComment = ticketCommentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ticketComment);
    }

    /**
     * {@code DELETE  /ticket-comments/:id} : delete the "id" ticketComment.
     *
     * @param id the id of the ticketComment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ticket-comments/{id}")
    public ResponseEntity<Void> deleteTicketComment(@PathVariable String id) {
        log.debug("REST request to delete TicketComment : {}", id);
        ticketCommentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
