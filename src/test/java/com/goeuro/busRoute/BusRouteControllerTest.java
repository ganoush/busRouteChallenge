package com.goeuro.busRoute;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ganeshnagarajan on 12/29/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BusRouteApplication.class)
@WebAppConfiguration
@TestPropertySource("classpath:testApplication.properties")
public class BusRouteControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void searchDirectBusRouteDoesNotExists() throws Exception {
        this.mockMvc.perform(get("/api/direct?dep_sid=153&arr_sid=184"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dep_sid").value(153))
                .andExpect(jsonPath("$.arr_sid").value(184))
                .andExpect(jsonPath("$.direct_bus_route").value(false));
    }

    @Test
    public void searchDirectBusRouteExists() throws Exception {
        this.mockMvc.perform(get("/api/direct?dep_sid=114&arr_sid=169"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dep_sid").value(114))
                .andExpect(jsonPath("$.arr_sid").value(169))
                .andExpect(jsonPath("$.direct_bus_route").value(true));
    }
}
