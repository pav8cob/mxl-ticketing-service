package com.ivert.mxl.tickets.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ivert.mxl.tickets.IntegrationTest;
import com.ivert.mxl.tickets.domain.TicketFilter;
import com.ivert.mxl.tickets.repository.TicketFilterRepository;
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
 * Integration tests for the {@link TicketFilterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TicketFilterResourceIT {

    private static final String DEFAULT_FILTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILTER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILTER = "AAAAAAAAAA";
    private static final String UPDATED_FILTER = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/ticket-filters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private TicketFilterRepository ticketFilterRepository;

    @Autowired
    private MockMvc restTicketFilterMockMvc;

    private TicketFilter ticketFilter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketFilter createEntity() {
        TicketFilter ticketFilter = new TicketFilter()
            .filterName(DEFAULT_FILTER_NAME)
            .filter(DEFAULT_FILTER)
            .userId(DEFAULT_USER_ID)
            .createDate(DEFAULT_CREATE_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return ticketFilter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TicketFilter createUpdatedEntity() {
        TicketFilter ticketFilter = new TicketFilter()
            .filterName(UPDATED_FILTER_NAME)
            .filter(UPDATED_FILTER)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);
        return ticketFilter;
    }

    @BeforeEach
    public void initTest() {
        ticketFilterRepository.deleteAll();
        ticketFilter = createEntity();
    }

    @Test
    void createTicketFilter() throws Exception {
        int databaseSizeBeforeCreate = ticketFilterRepository.findAll().size();
        // Create the TicketFilter
        restTicketFilterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketFilter)))
            .andExpect(status().isCreated());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeCreate + 1);
        TicketFilter testTicketFilter = ticketFilterList.get(ticketFilterList.size() - 1);
        assertThat(testTicketFilter.getFilterName()).isEqualTo(DEFAULT_FILTER_NAME);
        assertThat(testTicketFilter.getFilter()).isEqualTo(DEFAULT_FILTER);
        assertThat(testTicketFilter.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTicketFilter.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTicketFilter.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTicketFilter.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTicketFilter.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    }

    @Test
    void createTicketFilterWithExistingId() throws Exception {
        // Create the TicketFilter with an existing ID
        ticketFilter.setId("existing_id");

        int databaseSizeBeforeCreate = ticketFilterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTicketFilterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketFilter)))
            .andExpect(status().isBadRequest());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkFilterNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = ticketFilterRepository.findAll().size();
        // set the field null
        ticketFilter.setFilterName(null);

        // Create the TicketFilter, which fails.

        restTicketFilterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketFilter)))
            .andExpect(status().isBadRequest());

        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFilterIsRequired() throws Exception {
        int databaseSizeBeforeTest = ticketFilterRepository.findAll().size();
        // set the field null
        ticketFilter.setFilter(null);

        // Create the TicketFilter, which fails.

        restTicketFilterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketFilter)))
            .andExpect(status().isBadRequest());

        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllTicketFilters() throws Exception {
        // Initialize the database
        ticketFilterRepository.save(ticketFilter);

        // Get all the ticketFilterList
        restTicketFilterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ticketFilter.getId())))
            .andExpect(jsonPath("$.[*].filterName").value(hasItem(DEFAULT_FILTER_NAME)))
            .andExpect(jsonPath("$.[*].filter").value(hasItem(DEFAULT_FILTER)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.intValue())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY.intValue())));
    }

    @Test
    void getTicketFilter() throws Exception {
        // Initialize the database
        ticketFilterRepository.save(ticketFilter);

        // Get the ticketFilter
        restTicketFilterMockMvc
            .perform(get(ENTITY_API_URL_ID, ticketFilter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ticketFilter.getId()))
            .andExpect(jsonPath("$.filterName").value(DEFAULT_FILTER_NAME))
            .andExpect(jsonPath("$.filter").value(DEFAULT_FILTER))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.intValue()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY.intValue()));
    }

    @Test
    void getNonExistingTicketFilter() throws Exception {
        // Get the ticketFilter
        restTicketFilterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putNewTicketFilter() throws Exception {
        // Initialize the database
        ticketFilterRepository.save(ticketFilter);

        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();

        // Update the ticketFilter
        TicketFilter updatedTicketFilter = ticketFilterRepository.findById(ticketFilter.getId()).get();
        updatedTicketFilter
            .filterName(UPDATED_FILTER_NAME)
            .filter(UPDATED_FILTER)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTicketFilterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTicketFilter.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTicketFilter))
            )
            .andExpect(status().isOk());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
        TicketFilter testTicketFilter = ticketFilterList.get(ticketFilterList.size() - 1);
        assertThat(testTicketFilter.getFilterName()).isEqualTo(UPDATED_FILTER_NAME);
        assertThat(testTicketFilter.getFilter()).isEqualTo(UPDATED_FILTER);
        assertThat(testTicketFilter.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTicketFilter.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTicketFilter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTicketFilter.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTicketFilter.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    void putNonExistingTicketFilter() throws Exception {
        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();
        ticketFilter.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketFilterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ticketFilter.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticketFilter))
            )
            .andExpect(status().isBadRequest());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTicketFilter() throws Exception {
        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();
        ticketFilter.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketFilterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ticketFilter))
            )
            .andExpect(status().isBadRequest());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTicketFilter() throws Exception {
        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();
        ticketFilter.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketFilterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ticketFilter)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTicketFilterWithPatch() throws Exception {
        // Initialize the database
        ticketFilterRepository.save(ticketFilter);

        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();

        // Update the ticketFilter using partial update
        TicketFilter partialUpdatedTicketFilter = new TicketFilter();
        partialUpdatedTicketFilter.setId(ticketFilter.getId());

        partialUpdatedTicketFilter
            .filterName(UPDATED_FILTER_NAME)
            .filter(UPDATED_FILTER)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTicketFilterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicketFilter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicketFilter))
            )
            .andExpect(status().isOk());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
        TicketFilter testTicketFilter = ticketFilterList.get(ticketFilterList.size() - 1);
        assertThat(testTicketFilter.getFilterName()).isEqualTo(UPDATED_FILTER_NAME);
        assertThat(testTicketFilter.getFilter()).isEqualTo(UPDATED_FILTER);
        assertThat(testTicketFilter.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTicketFilter.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testTicketFilter.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTicketFilter.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTicketFilter.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    void fullUpdateTicketFilterWithPatch() throws Exception {
        // Initialize the database
        ticketFilterRepository.save(ticketFilter);

        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();

        // Update the ticketFilter using partial update
        TicketFilter partialUpdatedTicketFilter = new TicketFilter();
        partialUpdatedTicketFilter.setId(ticketFilter.getId());

        partialUpdatedTicketFilter
            .filterName(UPDATED_FILTER_NAME)
            .filter(UPDATED_FILTER)
            .userId(UPDATED_USER_ID)
            .createDate(UPDATED_CREATE_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTicketFilterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTicketFilter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTicketFilter))
            )
            .andExpect(status().isOk());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
        TicketFilter testTicketFilter = ticketFilterList.get(ticketFilterList.size() - 1);
        assertThat(testTicketFilter.getFilterName()).isEqualTo(UPDATED_FILTER_NAME);
        assertThat(testTicketFilter.getFilter()).isEqualTo(UPDATED_FILTER);
        assertThat(testTicketFilter.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTicketFilter.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testTicketFilter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTicketFilter.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTicketFilter.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    void patchNonExistingTicketFilter() throws Exception {
        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();
        ticketFilter.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTicketFilterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ticketFilter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticketFilter))
            )
            .andExpect(status().isBadRequest());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTicketFilter() throws Exception {
        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();
        ticketFilter.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketFilterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ticketFilter))
            )
            .andExpect(status().isBadRequest());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTicketFilter() throws Exception {
        int databaseSizeBeforeUpdate = ticketFilterRepository.findAll().size();
        ticketFilter.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTicketFilterMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ticketFilter))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TicketFilter in the database
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTicketFilter() throws Exception {
        // Initialize the database
        ticketFilterRepository.save(ticketFilter);

        int databaseSizeBeforeDelete = ticketFilterRepository.findAll().size();

        // Delete the ticketFilter
        restTicketFilterMockMvc
            .perform(delete(ENTITY_API_URL_ID, ticketFilter.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TicketFilter> ticketFilterList = ticketFilterRepository.findAll();
        assertThat(ticketFilterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
