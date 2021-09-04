package com.ivert.mxl.tickets.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ivert.mxl.tickets.IntegrationTest;
import com.ivert.mxl.tickets.domain.TicketComment;
import com.ivert.mxl.tickets.repository.TicketCommentRepository;
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
 * Integration tests for the {@link TicketCommentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TicketCommentResourceIT {

    private static final String DEFAULT_BODY = "AAAAAAAAAA";
    private static final String UPDATED_BODY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_PUBLIC = false;
    private static final Boolean UPDATED_IS_PUBLIC = true;

    private static final Long DEFAULT_AUTHOR_ID = 1L;
    private static final Long UPDATED_AUTHOR_ID = 2L;

    private static final Long DEFAULT_REPLYTO = 1L;
    private static final Long UPDATED_REPLYTO = 2L;

    private static final String DEFAULT_ATTACHMENTS = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENTS = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/ticket-comments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private TicketCommentRepository ticketCommentRepository;

    @Autowired
    private MockMvc restTicketCommentMockMvc;

    private TicketComment ticketComment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketComment createEntity() {
        TicketComment ticketComment = new TicketComment()
            .body(DEFAULT_BODY)
            .isPublic(DEFAULT_IS_PUBLIC)
            .authorId(DEFAULT_AUTHOR_ID)
            .replyto(DEFAULT_REPLYTO)
            .attachments(DEFAULT_ATTACHMENTS)
            .userId(DEFAULT_USER_ID)
            .createDate(DEFAULT_CREATE_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return ticketComment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketComment createUpdatedEntity() {
        TicketComment ticketComment = new TicketComment()
            .body(UPDATED_BODY)
            .isPublic(UPDATED_IS_PUBLIC)
            .authorId(UPDATED_AUTHOR_ID)
            .replyto(UPDATED_REPLYTO)
            .attachments(UPDATED_ATTACHMENTS)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);
        return ticketComment;
    }

    @BeforeEach
    public void initTest() {
        ticketCommentRepository.deleteAll();
        ticketComment = createEntity();
    }

    @Test
    void createTicketComment() throws Exception {
        int databaseSizeBeforeCreate = ticketCommentRepository.findAll().size();
        // Create the TicketComment
        restTicketCommentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketComment)))
            .andExpect(status().isCreated());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeCreate + 1);
        TicketComment testTicketComment = ticketCommentList.get(ticketCommentList.size() - 1);
        assertThat(testTicketComment.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testTicketComment.getIsPublic()).isEqualTo(DEFAULT_IS_PUBLIC);
        assertThat(testTicketComment.getAuthorId()).isEqualTo(DEFAULT_AUTHOR_ID);
        assertThat(testTicketComment.getReplyto()).isEqualTo(DEFAULT_REPLYTO);
        assertThat(testTicketComment.getAttachments()).isEqualTo(DEFAULT_ATTACHMENTS);
        assertThat(testTicketComment.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTicketComment.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTicketComment.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTicketComment.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTicketComment.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    void createTicketCommentWithExistingId() throws Exception {
        // Create the TicketComment with an existing ID
        ticketComment.setId("existing_id");

        int databaseSizeBeforeCreate = ticketCommentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketCommentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketComment)))
            .andExpect(status().isBadRequest());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkBodyIsRequired() throws Exception {
        int databaseSizeBeforeTest = ticketCommentRepository.findAll().size();
        // set the field null
        ticketComment.setBody(null);

        // Create the TicketComment, which fails.

        restTicketCommentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketComment)))
            .andExpect(status().isBadRequest());

        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllTicketComments() throws Exception {
        // Initialize the database
        ticketCommentRepository.save(ticketComment);

        // Get all the ticketCommentList
        restTicketCommentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticketComment.getId())))
            .andExpect(jsonPath("$.[*].body").value(hasItem(DEFAULT_BODY)))
            .andExpect(jsonPath("$.[*].isPublic").value(hasItem(DEFAULT_IS_PUBLIC.booleanValue())))
            .andExpect(jsonPath("$.[*].authorId").value(hasItem(DEFAULT_AUTHOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].replyto").value(hasItem(DEFAULT_REPLYTO.intValue())))
            .andExpect(jsonPath("$.[*].attachments").value(hasItem(DEFAULT_ATTACHMENTS)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())));
    }

    @Test
    void getTicketComment() throws Exception {
        // Initialize the database
        ticketCommentRepository.save(ticketComment);

        // Get the ticketComment
        restTicketCommentMockMvc
            .perform(get(ENTITY_API_URL_ID, ticketComment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ticketComment.getId()))
            .andExpect(jsonPath("$.body").value(DEFAULT_BODY))
            .andExpect(jsonPath("$.isPublic").value(DEFAULT_IS_PUBLIC.booleanValue()))
            .andExpect(jsonPath("$.authorId").value(DEFAULT_AUTHOR_ID.intValue()))
            .andExpect(jsonPath("$.replyto").value(DEFAULT_REPLYTO.intValue()))
            .andExpect(jsonPath("$.attachments").value(DEFAULT_ATTACHMENTS))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()));
    }

    @Test
    void getNonExistingTicketComment() throws Exception {
        // Get the ticketComment
        restTicketCommentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewTicketComment() throws Exception {
        // Initialize the database
        ticketCommentRepository.save(ticketComment);

        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();

        // Update the ticketComment
        TicketComment updatedTicketComment = ticketCommentRepository.findById(ticketComment.getId()).get();
        updatedTicketComment
            .body(UPDATED_BODY)
            .isPublic(UPDATED_IS_PUBLIC)
            .authorId(UPDATED_AUTHOR_ID)
            .replyto(UPDATED_REPLYTO)
            .attachments(UPDATED_ATTACHMENTS)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTicketCommentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTicketComment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTicketComment))
            )
            .andExpect(status().isOk());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
        TicketComment testTicketComment = ticketCommentList.get(ticketCommentList.size() - 1);
        assertThat(testTicketComment.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testTicketComment.getIsPublic()).isEqualTo(UPDATED_IS_PUBLIC);
        assertThat(testTicketComment.getAuthorId()).isEqualTo(UPDATED_AUTHOR_ID);
        assertThat(testTicketComment.getReplyto()).isEqualTo(UPDATED_REPLYTO);
        assertThat(testTicketComment.getAttachments()).isEqualTo(UPDATED_ATTACHMENTS);
        assertThat(testTicketComment.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTicketComment.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTicketComment.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTicketComment.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTicketComment.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    void putNonExistingTicketComment() throws Exception {
        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();
        ticketComment.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketCommentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ticketComment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticketComment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTicketComment() throws Exception {
        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();
        ticketComment.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketCommentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticketComment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTicketComment() throws Exception {
        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();
        ticketComment.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketCommentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketComment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTicketCommentWithPatch() throws Exception {
        // Initialize the database
        ticketCommentRepository.save(ticketComment);

        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();

        // Update the ticketComment using partial update
        TicketComment partialUpdatedTicketComment = new TicketComment();
        partialUpdatedTicketComment.setId(ticketComment.getId());

        partialUpdatedTicketComment
            .isPublic(UPDATED_IS_PUBLIC)
            .authorId(UPDATED_AUTHOR_ID)
            .attachments(UPDATED_ATTACHMENTS)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTicketCommentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicketComment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicketComment))
            )
            .andExpect(status().isOk());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
        TicketComment testTicketComment = ticketCommentList.get(ticketCommentList.size() - 1);
        assertThat(testTicketComment.getBody()).isEqualTo(DEFAULT_BODY);
        assertThat(testTicketComment.getIsPublic()).isEqualTo(UPDATED_IS_PUBLIC);
        assertThat(testTicketComment.getAuthorId()).isEqualTo(UPDATED_AUTHOR_ID);
        assertThat(testTicketComment.getReplyto()).isEqualTo(DEFAULT_REPLYTO);
        assertThat(testTicketComment.getAttachments()).isEqualTo(UPDATED_ATTACHMENTS);
        assertThat(testTicketComment.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTicketComment.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTicketComment.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTicketComment.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTicketComment.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    void fullUpdateTicketCommentWithPatch() throws Exception {
        // Initialize the database
        ticketCommentRepository.save(ticketComment);

        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();

        // Update the ticketComment using partial update
        TicketComment partialUpdatedTicketComment = new TicketComment();
        partialUpdatedTicketComment.setId(ticketComment.getId());

        partialUpdatedTicketComment
            .body(UPDATED_BODY)
            .isPublic(UPDATED_IS_PUBLIC)
            .authorId(UPDATED_AUTHOR_ID)
            .replyto(UPDATED_REPLYTO)
            .attachments(UPDATED_ATTACHMENTS)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTicketCommentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicketComment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicketComment))
            )
            .andExpect(status().isOk());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
        TicketComment testTicketComment = ticketCommentList.get(ticketCommentList.size() - 1);
        assertThat(testTicketComment.getBody()).isEqualTo(UPDATED_BODY);
        assertThat(testTicketComment.getIsPublic()).isEqualTo(UPDATED_IS_PUBLIC);
        assertThat(testTicketComment.getAuthorId()).isEqualTo(UPDATED_AUTHOR_ID);
        assertThat(testTicketComment.getReplyto()).isEqualTo(UPDATED_REPLYTO);
        assertThat(testTicketComment.getAttachments()).isEqualTo(UPDATED_ATTACHMENTS);
        assertThat(testTicketComment.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTicketComment.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTicketComment.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTicketComment.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTicketComment.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    void patchNonExistingTicketComment() throws Exception {
        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();
        ticketComment.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketCommentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ticketComment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticketComment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTicketComment() throws Exception {
        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();
        ticketComment.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketCommentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticketComment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTicketComment() throws Exception {
        int databaseSizeBeforeUpdate = ticketCommentRepository.findAll().size();
        ticketComment.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketCommentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ticketComment))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TicketComment in the database
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTicketComment() throws Exception {
        // Initialize the database
        ticketCommentRepository.save(ticketComment);

        int databaseSizeBeforeDelete = ticketCommentRepository.findAll().size();

        // Delete the ticketComment
        restTicketCommentMockMvc
            .perform(delete(ENTITY_API_URL_ID, ticketComment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TicketComment> ticketCommentList = ticketCommentRepository.findAll();
        assertThat(ticketCommentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
