package com.example.crudmysql;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static javafx.beans.binding.Bindings.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {
    private static final String PATH = "/users";
    private MockMvc mockMvc;
    private String requestJson;
    @InjectMocks
    private PersonController orderController;
    @Mock
    private PersonService service;

    @Before
    public void setUp() throws Exception{
        this.service = new PersonService();
        File file = ResourceUtils.getFile("classpath:dto/person.json");
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        ObjectMapper mapper = new ObjectMapper();
        PersonDto orderRequestDto = mapper.readValue(file, PersonDto.class);
        requestJson = mapper.writeValueAsString(orderRequestDto);
    }

    @Test
    public void savePerson_expectSuccess() throws Exception{
        this.mockMvc
                .perform(post("/api/users")
                    .contentType(APPLICATION_JSON)
                    .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getPersonById_expectSuccess() throws Exception{
        this.mockMvc
                .perform(get("/api/users/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

}