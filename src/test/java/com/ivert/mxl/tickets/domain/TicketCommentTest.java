package com.ivert.mxl.tickets.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ivert.mxl.tickets.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TicketCommentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicketComment.class);
        TicketComment ticketComment1 = new TicketComment();
        ticketComment1.setId("id1");
        TicketComment ticketComment2 = new TicketComment();
        ticketComment2.setId(ticketComment1.getId());
        assertThat(ticketComment1).isEqualTo(ticketComment2);
        ticketComment2.setId("id2");
        assertThat(ticketComment1).isNotEqualTo(ticketComment2);
        ticketComment1.setId(null);
        assertThat(ticketComment1).isNotEqualTo(ticketComment2);
    }
}
