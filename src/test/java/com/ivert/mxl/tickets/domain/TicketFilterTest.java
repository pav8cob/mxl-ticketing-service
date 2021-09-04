package com.ivert.mxl.tickets.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ivert.mxl.tickets.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TicketFilterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TicketFilter.class);
        TicketFilter ticketFilter1 = new TicketFilter();
        ticketFilter1.setId("id1");
        TicketFilter ticketFilter2 = new TicketFilter();
        ticketFilter2.setId(ticketFilter1.getId());
        assertThat(ticketFilter1).isEqualTo(ticketFilter2);
        ticketFilter2.setId("id2");
        assertThat(ticketFilter1).isNotEqualTo(ticketFilter2);
        ticketFilter1.setId(null);
        assertThat(ticketFilter1).isNotEqualTo(ticketFilter2);
    }
}
