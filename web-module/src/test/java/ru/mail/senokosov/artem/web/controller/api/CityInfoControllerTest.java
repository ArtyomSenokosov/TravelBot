package ru.mail.senokosov.artem.web.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.mail.senokosov.artem.service.CityInfoService;
import ru.mail.senokosov.artem.web.controller.CityController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mail.senokosov.artem.web.constant.PathConstant.*;

@WebMvcTest(controllers = CityController.class)
@ActiveProfiles("test")
public class CityInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CityInfoService cityInfoService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should200WhenWeRequestDeleteCityInfoById() throws Exception {
        Long id = 1L;
        when(cityInfoService.deleteCityInfoById(id)).thenReturn(true);
        mockMvc.perform(delete(String.format("%s%s/%s", REST_API_CITY_PATH, CITY_INFO_PATH, id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should404WhenWeRequestDeleteCityInfoByWrongId() throws Exception {
        Long id = 1L;
        when(cityInfoService.deleteCityInfoById(id)).thenReturn(false);
        mockMvc.perform(delete(String.format("%s%s/%s", REST_API_CITY_PATH, CITY_INFO_PATH, id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
