package com.ivert.mxl.tickets.web.rest;

import com.ivert.mxl.tickets.domain.TicketFilter;
import com.ivert.mxl.tickets.repository.TicketFilterRepository;
import com.ivert.mxl.tickets.service.TicketFilterService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ivert.mxl.tickets.domain.TicketFilter}.
 */
@RestController
@RequestMapping("/api")
public class TicketFilterResource {

    private final Logger log = LoggerFactory.getLogger(TicketFilterResource.class);

    private static final String ENTITY_NAME = "mxlTicketingTicketFilter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TicketFilterService ticketFilterService;

    private final TicketFilterRepository ticketFilterRepository;

    public TicketFilterResource(TicketFilterService ticketFilterService, TicketFilterRepository ticketFilterRepository) {
        this.ticketFilterService = ticketFilterService;
        this.ticketFilterRepository = ticketFilterRepository;
    }

    /**
     * {@code POST  /ticket-filters} : Create a new ticketFilter.
     *
     * @param ticketFilter the ticketFilter to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ticketFilter, or with status {@code 400 (Bad Request)} if the ticketFilter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ticket-filters")
    public ResponseEntity<TicketFilter> createTicketFilter(@Valid @RequestBody TicketFilter ticketFilter) throws URISyntaxException {
        log.debug("REST request to save TicketFilter : {}", ticketFilter);
        if (ticketFilter.getId() != null) {
            throw new BadRequestAlertException("A new ticketFilter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TicketFilter result = ticketFilterService.save(ticketFilter);
        return ResponseEntity
            .created(new URI("/api/ticket-filters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /ticket-filters/:id} : Updates an existing ticketFilter.
     *
     * @param id the id of the ticketFilter to save.
     * @param ticketFilter the ticketFilter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketFilter,
     * or with status {@code 400 (Bad Request)} if the ticketFilter is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ticketFilter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ticket-filters/{id}")
    public ResponseEntity<TicketFilter> updateTicketFilter(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody TicketFilter ticketFilter
    ) throws URISyntaxException {
        log.debug("REST request to update TicketFilter : {}, {}", id, ticketFilter);
        if (ticketFilter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ticketFilter.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ticketFilterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TicketFilter result = ticketFilterService.save(ticketFilter);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ticketFilter.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /ticket-filters/:id} : Partial updates given fields of an existing ticketFilter, field will ignore if it is null
     *
     * @param id the id of the ticketFilter to save.
     * @param ticketFilter the ticketFilter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketFilter,
     * or with status {@code 400 (Bad Request)} if the ticketFilter is not valid,
     * or with status {@code 404 (Not Found)} if the ticketFilter is not found,
     * or with status {@code 500 (Internal Server Error)} if the ticketFilter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ticket-filters/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TicketFilter> partialUpdateTicketFilter(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody TicketFilter ticketFilter
    ) throws URISyntaxException {
        log.debug("REST request to partial update TicketFilter partially : {}, {}", id, ticketFilter);
        if (ticketFilter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ticketFilter.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ticketFilterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TicketFilter> result = ticketFilterService.partialUpdate(ticketFilter);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ticketFilter.getId())
        );
    }

    /**
     * {@code GET  /ticket-filters} : get all the ticketFilters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ticketFilters in body.
     */
    @GetMapping("/ticket-filters")
    public List<TicketFilter> getAllTicketFilters() {
        log.debug("REST request to get all TicketFilters");
        return ticketFilterService.findAll();
    }

    /**
     * {@code GET  /ticket-filters/:id} : get the "id" ticketFilter.
     *
     * @param id the id of the ticketFilter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ticketFilter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ticket-filters/{id}")
    public ResponseEntity<TicketFilter> getTicketFilter(@PathVariable String id) {
        log.debug("REST request to get TicketFilter : {}", id);
        Optional<TicketFilter> ticketFilter = ticketFilterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ticketFilter);
    }

    /**
     * {@code DELETE  /ticket-filters/:id} : delete the "id" ticketFilter.
     *
     * @param id the id of the ticketFilter to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ticket-filters/{id}")
    public ResponseEntity<Void> deleteTicketFilter(@PathVariable String id) {
        log.debug("REST request to delete TicketFilter : {}", id);
        ticketFilterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
