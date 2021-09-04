package com.ivert.mxl.tickets.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A TicketFilter.
 */
@Document(collection = "ticket_filter")
public class TicketFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("filter_name")
    private String filterName;

    @NotNull
    @Field("filter")
    private String filter;

    @Field("user_id")
    private Long userId;

    @Field("create_date")
    private Instant createDate;

    @Field("created_by")
    private Long createdBy;

    @Field("modified_date")
    private Instant modifiedDate;

    @Field("modified_by")
    private Long modifiedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TicketFilter id(String id) {
        this.id = id;
        return this;
    }

    public String getFilterName() {
        return this.filterName;
    }

    public TicketFilter filterName(String filterName) {
        this.filterName = filterName;
        return this;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilter() {
        return this.filter;
    }

    public TicketFilter filter(String filter) {
        this.filter = filter;
        return this;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Long getUserId() {
        return this.userId;
    }

    public TicketFilter userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public TicketFilter createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public TicketFilter createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public TicketFilter modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getModifiedBy() {
        return this.modifiedBy;
    }

    public TicketFilter modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketFilter)) {
            return false;
        }
        return id != null && id.equals(((TicketFilter) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketFilter{" +
            "id=" + getId() +
            ", filterName='" + getFilterName() + "'" +
            ", filter='" + getFilter() + "'" +
            ", userId=" + getUserId() +
            ", createDate='" + getCreateDate() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            "}";
    }
}
