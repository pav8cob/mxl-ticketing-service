package com.ivert.mxl.tickets.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ivert.mxl.tickets.IntegrationTest;
import com.ivert.mxl.tickets.domain.Ticket;
import com.ivert.mxl.tickets.repository.TicketRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link TicketResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TicketResourceIT {

    private static final String DEFAULT_SOURCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBJECT = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRIORITY = "AAAAAAAAAA";
    private static final String UPDATED_PRIORITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_OWNER_ID = 1L;
    private static final Long UPDATED_OWNER_ID = 2L;

    private static final Long DEFAULT_ASSIGNED_ID = 1L;
    private static final Long UPDATED_ASSIGNED_ID = 2L;

    private static final Long DEFAULT_ASSIGNED_GROUP_ID = 1L;
    private static final Long UPDATED_ASSIGNED_GROUP_ID = 2L;

    private static final String DEFAULT_TAGS = "AAAAAAAAAA";
    private static final String UPDATED_TAGS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PRIVATE = false;
    private static final Boolean UPDATED_IS_PRIVATE = true;

    private static final Instant DEFAULT_DUE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DUE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_FOLLOWTO = 1L;
    private static final Long UPDATED_FOLLOWTO = 2L;

    private static final Boolean DEFAULT_NOTIFY = false;
    private static final Boolean UPDATED_NOTIFY = true;

    private static final Instant DEFAULT_NOTIFICATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_NOTIFICATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CREATED_BY = 1L;
    private static final Long UPDATED_CREATED_BY = 2L;

    private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_MODIFIED_BY = 1L;
    private static final Long UPDATED_MODIFIED_BY = 2L;

    private static final String ENTITY_API_URL = "/api/tickets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MockMvc restTicketMockMvc;

    private Ticket ticket;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ticket createEntity() {
        Ticket ticket = new Ticket()
            .sourceId(DEFAULT_SOURCE_ID)
            .sourceType(DEFAULT_SOURCE_TYPE)
            .type(DEFAULT_TYPE)
            .subject(DEFAULT_SUBJECT)
            .description(DEFAULT_DESCRIPTION)
            .priority(DEFAULT_PRIORITY)
            .status(DEFAULT_STATUS)
            .ownerId(DEFAULT_OWNER_ID)
            .assignedId(DEFAULT_ASSIGNED_ID)
            .assignedGroupId(DEFAULT_ASSIGNED_GROUP_ID)
            .tags(DEFAULT_TAGS)
            .isPrivate(DEFAULT_IS_PRIVATE)
            .dueDate(DEFAULT_DUE_DATE)
            .followto(DEFAULT_FOLLOWTO)
            .notify(DEFAULT_NOTIFY)
            .notificationTime(DEFAULT_NOTIFICATION_TIME)
            .userId(DEFAULT_USER_ID)
            .createDate(DEFAULT_CREATE_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return ticket;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ticket createUpdatedEntity() {
        Ticket ticket = new Ticket()
            .sourceId(UPDATED_SOURCE_ID)
            .sourceType(UPDATED_SOURCE_TYPE)
            .type(UPDATED_TYPE)
            .subject(UPDATED_SUBJECT)
            .description(UPDATED_DESCRIPTION)
            .priority(UPDATED_PRIORITY)
            .status(UPDATED_STATUS)
            .ownerId(UPDATED_OWNER_ID)
            .assignedId(UPDATED_ASSIGNED_ID)
            .assignedGroupId(UPDATED_ASSIGNED_GROUP_ID)
            .tags(UPDATED_TAGS)
            .isPrivate(UPDATED_IS_PRIVATE)
            .dueDate(UPDATED_DUE_DATE)
            .followto(UPDATED_FOLLOWTO)
            .notify(UPDATED_NOTIFY)
            .notificationTime(UPDATED_NOTIFICATION_TIME)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);
        return ticket;
    }

    @BeforeEach
    public void initTest() {
        ticketRepository.deleteAll();
        ticket = createEntity();
    }

    @Test
    void createTicket() throws Exception {
        int databaseSizeBeforeCreate = ticketRepository.findAll().size();
        // Create the Ticket
        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isCreated());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeCreate + 1);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testTicket.getSourceType()).isEqualTo(DEFAULT_SOURCE_TYPE);
        assertThat(testTicket.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTicket.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testTicket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTicket.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testTicket.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTicket.getOwnerId()).isEqualTo(DEFAULT_OWNER_ID);
        assertThat(testTicket.getAssignedId()).isEqualTo(DEFAULT_ASSIGNED_ID);
        assertThat(testTicket.getAssignedGroupId()).isEqualTo(DEFAULT_ASSIGNED_GROUP_ID);
        assertThat(testTicket.getTags()).isEqualTo(DEFAULT_TAGS);
        assertThat(testTicket.getIsPrivate()).isEqualTo(DEFAULT_IS_PRIVATE);
        assertThat(testTicket.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testTicket.getFollowto()).isEqualTo(DEFAULT_FOLLOWTO);
        assertThat(testTicket.getNotify()).isEqualTo(DEFAULT_NOTIFY);
        assertThat(testTicket.getNotificationTime()).isEqualTo(DEFAULT_NOTIFICATION_TIME);
        assertThat(testTicket.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTicket.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTicket.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTicket.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTicket.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    void createTicketWithExistingId() throws Exception {
        // Create the Ticket with an existing ID
        ticket.setId("existing_id");

        int databaseSizeBeforeCreate = ticketRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkSubjectIsRequired() throws Exception {
        int databaseSizeBeforeTest = ticketRepository.findAll().size();
        // set the field null
        ticket.setSubject(null);

        // Create the Ticket, which fails.

        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isBadRequest());

        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = ticketRepository.findAll().size();
        // set the field null
        ticket.setDescription(null);

        // Create the Ticket, which fails.

        restTicketMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isBadRequest());

        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllTickets() throws Exception {
        // Initialize the database
        ticketRepository.save(ticket);

        // Get all the ticketList
        restTicketMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticket.getId())))
            .andExpect(jsonPath("$.[*].sourceId").value(hasItem(DEFAULT_SOURCE_ID)))
            .andExpect(jsonPath("$.[*].sourceType").value(hasItem(DEFAULT_SOURCE_TYPE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].ownerId").value(hasItem(DEFAULT_OWNER_ID.intValue())))
            .andExpect(jsonPath("$.[*].assignedId").value(hasItem(DEFAULT_ASSIGNED_ID.intValue())))
            .andExpect(jsonPath("$.[*].assignedGroupId").value(hasItem(DEFAULT_ASSIGNED_GROUP_ID.intValue())))
            .andExpect(jsonPath("$.[*].tags").value(hasItem(DEFAULT_TAGS)))
            .andExpect(jsonPath("$.[*].isPrivate").value(hasItem(DEFAULT_IS_PRIVATE.booleanValue())))
            .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
            .andExpect(jsonPath("$.[*].followto").value(hasItem(DEFAULT_FOLLOWTO.intValue())))
            .andExpect(jsonPath("$.[*].notify").value(hasItem(DEFAULT_NOTIFY.booleanValue())))
            .andExpect(jsonPath("$.[*].notificationTime").value(hasItem(DEFAULT_NOTIFICATION_TIME.toString())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())));
    }

    @Test
    void getTicket() throws Exception {
        // Initialize the database
        ticketRepository.save(ticket);

        // Get the ticket
        restTicketMockMvc
            .perform(get(ENTITY_API_URL_ID, ticket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ticket.getId()))
            .andExpect(jsonPath("$.sourceId").value(DEFAULT_SOURCE_ID))
            .andExpect(jsonPath("$.sourceType").value(DEFAULT_SOURCE_TYPE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.ownerId").value(DEFAULT_OWNER_ID.intValue()))
            .andExpect(jsonPath("$.assignedId").value(DEFAULT_ASSIGNED_ID.intValue()))
            .andExpect(jsonPath("$.assignedGroupId").value(DEFAULT_ASSIGNED_GROUP_ID.intValue()))
            .andExpect(jsonPath("$.tags").value(DEFAULT_TAGS))
            .andExpect(jsonPath("$.isPrivate").value(DEFAULT_IS_PRIVATE.booleanValue()))
            .andExpect(jsonPath("$.dueDate").value(DEFAULT_DUE_DATE.toString()))
            .andExpect(jsonPath("$.followto").value(DEFAULT_FOLLOWTO.intValue()))
            .andExpect(jsonPath("$.notify").value(DEFAULT_NOTIFY.booleanValue()))
            .andExpect(jsonPath("$.notificationTime").value(DEFAULT_NOTIFICATION_TIME.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()));
    }

    @Test
    void getNonExistingTicket() throws Exception {
        // Get the ticket
        restTicketMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewTicket() throws Exception {
        // Initialize the database
        ticketRepository.save(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket
        Ticket updatedTicket = ticketRepository.findById(ticket.getId()).get();
        updatedTicket
            .sourceId(UPDATED_SOURCE_ID)
            .sourceType(UPDATED_SOURCE_TYPE)
            .type(UPDATED_TYPE)
            .subject(UPDATED_SUBJECT)
            .description(UPDATED_DESCRIPTION)
            .priority(UPDATED_PRIORITY)
            .status(UPDATED_STATUS)
            .ownerId(UPDATED_OWNER_ID)
            .assignedId(UPDATED_ASSIGNED_ID)
            .assignedGroupId(UPDATED_ASSIGNED_GROUP_ID)
            .tags(UPDATED_TAGS)
            .isPrivate(UPDATED_IS_PRIVATE)
            .dueDate(UPDATED_DUE_DATE)
            .followto(UPDATED_FOLLOWTO)
            .notify(UPDATED_NOTIFY)
            .notificationTime(UPDATED_NOTIFICATION_TIME)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTicket.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTicket))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testTicket.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
        assertThat(testTicket.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTicket.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testTicket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTicket.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testTicket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTicket.getOwnerId()).isEqualTo(UPDATED_OWNER_ID);
        assertThat(testTicket.getAssignedId()).isEqualTo(UPDATED_ASSIGNED_ID);
        assertThat(testTicket.getAssignedGroupId()).isEqualTo(UPDATED_ASSIGNED_GROUP_ID);
        assertThat(testTicket.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testTicket.getIsPrivate()).isEqualTo(UPDATED_IS_PRIVATE);
        assertThat(testTicket.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testTicket.getFollowto()).isEqualTo(UPDATED_FOLLOWTO);
        assertThat(testTicket.getNotify()).isEqualTo(UPDATED_NOTIFY);
        assertThat(testTicket.getNotificationTime()).isEqualTo(UPDATED_NOTIFICATION_TIME);
        assertThat(testTicket.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTicket.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTicket.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTicket.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTicket.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    void putNonExistingTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ticket.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticket))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticket))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTicketWithPatch() throws Exception {
        // Initialize the database
        ticketRepository.save(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket using partial update
        Ticket partialUpdatedTicket = new Ticket();
        partialUpdatedTicket.setId(ticket.getId());

        partialUpdatedTicket
            .type(UPDATED_TYPE)
            .ownerId(UPDATED_OWNER_ID)
            .assignedId(UPDATED_ASSIGNED_ID)
            .assignedGroupId(UPDATED_ASSIGNED_GROUP_ID)
            .tags(UPDATED_TAGS)
            .dueDate(UPDATED_DUE_DATE)
            .followto(UPDATED_FOLLOWTO)
            .notificationTime(UPDATED_NOTIFICATION_TIME)
            .userId(UPDATED_USER_ID)
            .createdBy(UPDATED_CREATED_BY);

        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicket))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getSourceId()).isEqualTo(DEFAULT_SOURCE_ID);
        assertThat(testTicket.getSourceType()).isEqualTo(DEFAULT_SOURCE_TYPE);
        assertThat(testTicket.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTicket.getSubject()).isEqualTo(DEFAULT_SUBJECT);
        assertThat(testTicket.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTicket.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testTicket.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTicket.getOwnerId()).isEqualTo(UPDATED_OWNER_ID);
        assertThat(testTicket.getAssignedId()).isEqualTo(UPDATED_ASSIGNED_ID);
        assertThat(testTicket.getAssignedGroupId()).isEqualTo(UPDATED_ASSIGNED_GROUP_ID);
        assertThat(testTicket.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testTicket.getIsPrivate()).isEqualTo(DEFAULT_IS_PRIVATE);
        assertThat(testTicket.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testTicket.getFollowto()).isEqualTo(UPDATED_FOLLOWTO);
        assertThat(testTicket.getNotify()).isEqualTo(DEFAULT_NOTIFY);
        assertThat(testTicket.getNotificationTime()).isEqualTo(UPDATED_NOTIFICATION_TIME);
        assertThat(testTicket.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTicket.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTicket.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTicket.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTicket.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    void fullUpdateTicketWithPatch() throws Exception {
        // Initialize the database
        ticketRepository.save(ticket);

        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();

        // Update the ticket using partial update
        Ticket partialUpdatedTicket = new Ticket();
        partialUpdatedTicket.setId(ticket.getId());

        partialUpdatedTicket
            .sourceId(UPDATED_SOURCE_ID)
            .sourceType(UPDATED_SOURCE_TYPE)
            .type(UPDATED_TYPE)
            .subject(UPDATED_SUBJECT)
            .description(UPDATED_DESCRIPTION)
            .priority(UPDATED_PRIORITY)
            .status(UPDATED_STATUS)
            .ownerId(UPDATED_OWNER_ID)
            .assignedId(UPDATED_ASSIGNED_ID)
            .assignedGroupId(UPDATED_ASSIGNED_GROUP_ID)
            .tags(UPDATED_TAGS)
            .isPrivate(UPDATED_IS_PRIVATE)
            .dueDate(UPDATED_DUE_DATE)
            .followto(UPDATED_FOLLOWTO)
            .notify(UPDATED_NOTIFY)
            .notificationTime(UPDATED_NOTIFICATION_TIME)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicket))
            )
            .andExpect(status().isOk());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
        Ticket testTicket = ticketList.get(ticketList.size() - 1);
        assertThat(testTicket.getSourceId()).isEqualTo(UPDATED_SOURCE_ID);
        assertThat(testTicket.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
        assertThat(testTicket.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTicket.getSubject()).isEqualTo(UPDATED_SUBJECT);
        assertThat(testTicket.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTicket.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testTicket.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTicket.getOwnerId()).isEqualTo(UPDATED_OWNER_ID);
        assertThat(testTicket.getAssignedId()).isEqualTo(UPDATED_ASSIGNED_ID);
        assertThat(testTicket.getAssignedGroupId()).isEqualTo(UPDATED_ASSIGNED_GROUP_ID);
        assertThat(testTicket.getTags()).isEqualTo(UPDATED_TAGS);
        assertThat(testTicket.getIsPrivate()).isEqualTo(UPDATED_IS_PRIVATE);
        assertThat(testTicket.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testTicket.getFollowto()).isEqualTo(UPDATED_FOLLOWTO);
        assertThat(testTicket.getNotify()).isEqualTo(UPDATED_NOTIFY);
        assertThat(testTicket.getNotificationTime()).isEqualTo(UPDATED_NOTIFICATION_TIME);
        assertThat(testTicket.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTicket.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTicket.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTicket.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTicket.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    void patchNonExistingTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ticket.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticket))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticket))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTicket() throws Exception {
        int databaseSizeBeforeUpdate = ticketRepository.findAll().size();
        ticket.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ticket)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ticket in the database
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTicket() throws Exception {
        // Initialize the database
        ticketRepository.save(ticket);

        int databaseSizeBeforeDelete = ticketRepository.findAll().size();

        // Delete the ticket
        restTicketMockMvc
            .perform(delete(ENTITY_API_URL_ID, ticket.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ticket> ticketList = ticketRepository.findAll();
        assertThat(ticketList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
