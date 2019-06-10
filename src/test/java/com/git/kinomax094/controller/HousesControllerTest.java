package com.git.kinomax094.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.git.kinomax094.dto.DtoError;
import com.git.kinomax094.model.House;
import com.git.kinomax094.service.HouseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HouseController.class)
public class HousesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HouseService service;


    private JacksonTester<House> jsonHouse;
    private JacksonTester<DtoError> errorJacksonTester;

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
        JacksonTester.initFields(this, new ObjectMapper());
    }


    @Test
    public void getHouseByNameTestForStatus() throws Exception {
        House house = new House();


       given(service.getHouseByName("House_Allyrion_of_Godsgrace")).willReturn(house);

        MockHttpServletResponse response = this.mvc.perform(get("/getHouseByName/House_Allyrion_of_Godsgrace").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), jsonHouse.write(house).getJson() );

    }

    @Test
    public void getHouseByNameTestForWrongNameException() throws Exception {
        DtoError error = new DtoError();
        error.setMessage("Pod podaną nazwą nie znaleziono rodu (q : p)");


        given(service.getHouseByName("House_Allyrion_of_Godsgrac")).willReturn(null);

        MockHttpServletResponse response = this.mvc.perform(get("/getHouseByName/House_Allyrion_of_Godsgrac").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.NOT_FOUND.value());
        assertEquals(response.getContentAsString(), errorJacksonTester.write(error).getJson() );

    }


    @Test
    public void getHouseByNameTestForWrongPatternException() throws Exception {
        DtoError error = new DtoError();
        error.setMessage("Podano zły format nazwy");


        given(service.getHouseByName("HouseAllyrionofGodsgrace")).willReturn(null);

        MockHttpServletResponse response = this.mvc.perform(get("/getHouseByName/HouseAllyrionofGodsgrace").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.NOT_FOUND.value());
        assertEquals(response.getContentAsString(), errorJacksonTester.write(error).getJson() );

    }

}