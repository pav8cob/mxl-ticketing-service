package com.ivert.mxl.tickets.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Ticket.
 */
@Document(collection = "ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("source_id")
    private String sourceId;

    @Field("source_type")
    private String sourceType;

    @Field("type")
    private String type;

    @NotNull
    @Field("subject")
    private String subject;

    @NotNull
    @Field("description")
    private String description;

    @Field("priority")
    private String priority;

    @Field("status")
    private String status;

    @Field("owner_id")
    private Long ownerId;

    @Field("assigned_id")
    private Long assignedId;

    @Field("assigned_group_id")
    private Long assignedGroupId;

    @Field("tags")
    private String tags;

    @Field("is_private")
    private Boolean isPrivate;

    @Field("due_date")
    private Instant dueDate;

    @Field("followto")
    private Long followto;

    @Field("notify")
    private Boolean notify;

    @Field("notification_time")
    private Instant notificationTime;

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
    @Field("ticketComment")
    @JsonIgnoreProperties(value = { "employee" }, allowSetters = true)
    private Set<TicketComment> ticketComments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ticket id(String id) {
        this.id = id;
        return this;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public Ticket sourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public Ticket sourceType(String sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getType() {
        return this.type;
    }

    public Ticket type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return this.subject;
    }

    public Ticket subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return this.description;
    }

    public Ticket description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return this.priority;
    }

    public Ticket priority(String priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return this.status;
    }

    public Ticket status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public Ticket ownerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getAssignedId() {
        return this.assignedId;
    }

    public Ticket assignedId(Long assignedId) {
        this.assignedId = assignedId;
        return this;
    }

    public void setAssignedId(Long assignedId) {
        this.assignedId = assignedId;
    }

    public Long getAssignedGroupId() {
        return this.assignedGroupId;
    }

    public Ticket assignedGroupId(Long assignedGroupId) {
        this.assignedGroupId = assignedGroupId;
        return this;
    }

    public void setAssignedGroupId(Long assignedGroupId) {
        this.assignedGroupId = assignedGroupId;
    }

    public String getTags() {
        return this.tags;
    }

    public Ticket tags(String tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getIsPrivate() {
        return this.isPrivate;
    }

    public Ticket isPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Instant getDueDate() {
        return this.dueDate;
    }

    public Ticket dueDate(Instant dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public Long getFollowto() {
        return this.followto;
    }

    public Ticket followto(Long followto) {
        this.followto = followto;
        return this;
    }

    public void setFollowto(Long followto) {
        this.followto = followto;
    }

    public Boolean getNotify() {
        return this.notify;
    }

    public Ticket notify(Boolean notify) {
        this.notify = notify;
        return this;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public Instant getNotificationTime() {
        return this.notificationTime;
    }

    public Ticket notificationTime(Instant notificationTime) {
        this.notificationTime = notificationTime;
        return this;
    }

    public void setNotificationTime(Instant notificationTime) {
        this.notificationTime = notificationTime;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Ticket userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public Ticket createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public Ticket createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public Ticket modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getModifiedBy() {
        return this.modifiedBy;
    }

    public Ticket modifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Set<TicketComment> getTicketComments() {
        return this.ticketComments;
    }

    public Ticket ticketComments(Set<TicketComment> ticketComments) {
        this.setTicketComments(ticketComments);
        return this;
    }

    public Ticket addTicketComment(TicketComment ticketComment) {
        this.ticketComments.add(ticketComment);
        ticketComment.setEmployee(this);
        return this;
    }

    public Ticket removeTicketComment(TicketComment ticketComment) {
        this.ticketComments.remove(ticketComment);
        ticketComment.setEmployee(null);
        return this;
    }

    public void setTicketComments(Set<TicketComment> ticketComments) {
        if (this.ticketComments != null) {
            this.ticketComments.forEach(i -> i.setEmployee(null));
        }
        if (ticketComments != null) {
            ticketComments.forEach(i -> i.setEmployee(this));
        }
        this.ticketComments = ticketComments;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ticket{" +
            "id=" + getId() +
            ", sourceId='" + getSourceId() + "'" +
            ", sourceType='" + getSourceType() + "'" +
            ", type='" + getType() + "'" +
            ", subject='" + getSubject() + "'" +
            ", description='" + getDescription() + "'" +
            ", priority='" + getPriority() + "'" +
            ", status='" + getStatus() + "'" +
            ", ownerId=" + getOwnerId() +
            ", assignedId=" + getAssignedId() +
            ", assignedGroupId=" + getAssignedGroupId() +
            ", tags='" + getTags() + "'" +
            ", isPrivate='" + getIsPrivate() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", followto=" + getFollowto() +
            ", notify='" + getNotify() + "'" +
            ", notificationTime='" + getNotificationTime() + "'" +
            ", userId=" + getUserId() +
            ", createDate='" + getCreateDate() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", modifiedBy=" + getModifiedBy() +
            "}";
    }
}
