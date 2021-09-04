package com.ivert.mxl.tickets.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A TicketComment.
 */
@Document(collection = "ticket_comment")
public class TicketComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("body")
    private String body;

    @Field("is_public")
    private Boolean isPublic;

    @Field("author_id")
    private Long authorId;

    @Field("replyto")
    private Long replyto;

    @Field("attachments")
    private String attachments;

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

    @DBRef
    @Field("employee")
    @JsonIgnoreProperties(value = { "ticketComments" }, allowSetters = true)
    private Ticket employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TicketComment id(String id) {
        this.id = id;
        return this;
    }

    public String getBody() {
        return this.body;
    }

    public TicketComment body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getIsPublic() {
        return this.isPublic;
    }

    public TicketComment isPublic(Boolean isPublic) {
        this.isPublic = isPublic;
        return this;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public TicketComment authorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getReplyto() {
        return this.replyto;
    }

    public TicketComment replyto(Long replyto) {
        this.replyto = replyto;
        return this;
    }

    public void setReplyto(Long replyto) {
        this.replyto = replyto;
    }

    public String getAttachments() {
        return this.attachments;
    }

    public TicketComment attachments(String attachments) {
        this.attachments = attachments;
        return this;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Long getUserId() {
        return this.userId;
    }

    public TicketComment userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public TicketComment createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public TicketComment createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public TicketComment modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getModifiedBy() {
        return this.modifiedBy;
    }

    public TicketComment modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Ticket getEmployee() {
        return this.employee;
    }

    public TicketComment employee(Ticket ticket) {
        this.setEmployee(ticket);
        return this;
    }

    public void setEmployee(Ticket ticket) {
        this.employee = ticket;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketComment)) {
            return false;
        }
        return id != null && id.equals(((TicketComment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketComment{" +
            "id=" + getId() +
            ", body='" + getBody() + "'" +
            ", isPublic='" + getIsPublic() + "'" +
            ", authorId=" + getAuthorId() +
            ", replyto=" + getReplyto() +
            ", attachments='" + getAttachments() + "'" +
            ", userId=" + getUserId() +
            ", createDate='" + getCreateDate() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            "}";
    }
}
