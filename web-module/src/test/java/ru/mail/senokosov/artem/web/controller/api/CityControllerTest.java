package ru.mail.senokosov.artem.web.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.mail.senokosov.artem.service.CityService;
import ru.mail.senokosov.artem.service.model.CityDTO;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;
import ru.mail.senokosov.artem.web.controller.CityController;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mail.senokosov.artem.web.constant.PathConstant.CITY_PATH;
import static ru.mail.senokosov.artem.web.constant.PathConstant.REST_API_CITY_PATH;

@WebMvcTest(controllers = CityController.class)
@ActiveProfiles("test")
class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CityService cityService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllCitiesAndReturnOk() throws Exception {
        mockMvc.perform(get(REST_API_CITY_PATH + CITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldVerifyThatTheBusinessLogicWasCalledWhenWeRequestGetAllCities() throws Exception {
        mockMvc.perform(get(REST_API_CITY_PATH + CITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(cityService, times(1)).getAllCity();
    }

    @Test
    void shouldReturnCollectionOfCitiesWhenWeRequestGetAllCities() throws Exception {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(1L);
        cityDTO.setCityName("Test name");
        CityInfoDTO cityInfoDTO = new CityInfoDTO();
        cityInfoDTO.setId(1L);
        cityInfoDTO.setCityInfo("Test info");
        cityDTO.getCityInfo().add(cityInfoDTO);

        List<CityDTO> cities = Collections.singletonList(cityDTO);

        when(cityService.getAllCity()).thenReturn(cities);

        MvcResult mvcResult = mockMvc.perform(get(REST_API_CITY_PATH + CITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString).isEqualToIgnoringCase(objectMapper.writeValueAsString(cities));
    }

    @Test
    void shouldReturnCityWhenWeRequestGetCityById() throws Exception {
        CityDTO cityDTO = new CityDTO();
        Long id = 1L;
        cityDTO.setId(1L);
        cityDTO.setCityName("Test name");
        CityInfoDTO cityInfoDTO = new CityInfoDTO();
        cityInfoDTO.setId(1L);
        cityInfoDTO.setCityInfo("Test info");
        cityDTO.getCityInfo().add(cityInfoDTO);

        List<CityDTO> cities = Collections.singletonList(cityDTO);

        when(cityService.getCityById(id)).thenReturn(cityDTO);

        MvcResult mvcResult = mockMvc.perform(get(String.format("%s%s/%s", REST_API_CITY_PATH, CITY_PATH, id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        assertThat(contentAsString).isEqualToIgnoringCase(objectMapper.writeValueAsString(cities));
    }

    @Test
    void should404WhenWeRequestGetCityByWrongId() throws Exception {
        CityDTO cityDTO = new CityDTO();
        Long id = 1L;
        cityDTO.setId(1L);
        cityDTO.setCityName("Test name");
        CityInfoDTO cityInfoDTO = new CityInfoDTO();
        cityInfoDTO.setId(1L);
        cityInfoDTO.setCityInfo("Test info");
        cityDTO.getCityInfo().add(cityInfoDTO);

        List<CityDTO> cities = Collections.singletonList(cityDTO);

        when(cityService.getCityById(id)).thenReturn(cityDTO);
        Long wrongId = 2L;

        mockMvc.perform(get(String.format("%s%s/%s", REST_API_CITY_PATH, CITY_PATH, wrongId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void should201WhenWeRequestAddCity() throws Exception {
        mockMvc.perform(post(REST_API_CITY_PATH + CITY_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"city_name\": \"test name\",\n" +
                                "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void should200WhenWeRequestDeleteCityById() throws Exception {
        Long id = 1L;
        when(cityService.deleteCityById(id)).thenReturn(true);
        mockMvc.perform(delete(String.format("%s%s/%s", REST_API_CITY_PATH, CITY_PATH, id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should404WhenWeRequestDeleteCityByWrongId() throws Exception {
        Long id = 1L;
        when(cityService.deleteCityById(id)).thenReturn(false);
        mockMvc.perform(delete(String.format("%s%s/%s", REST_API_CITY_PATH, CITY_PATH, id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
