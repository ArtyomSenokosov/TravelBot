package ru.mail.senokosov.artem.service.converter.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.mail.senokosov.artem.repository.model.CityInfo;
import ru.mail.senokosov.artem.service.model.CityInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityInfoConverterImplTest {

    @InjectMocks
    private CityInfoConverterImpl cityInfoConverter;

    @Test
    void shouldConvertCityInfoToCityInfoDTOAndReturnRightId() {
        CityInfo cityInfo = new CityInfo();
        Long id = 1L;
        cityInfo.setId(id);
        CityInfoDTO cityInfoDTO = cityInfoConverter.convert(cityInfo);

        assertEquals(id, cityInfoDTO.getId());
    }

    @Test
    void shouldConvertCityInfoToCityInfoDTOAndReturnRightInfo() {
        CityInfo cityInfo = new CityInfo();
        String info = "test info";
        cityInfo.setCityInfo(info);
        CityInfoDTO cityInfoDTO = cityInfoConverter.convert(cityInfo);

        assertEquals(info, cityInfoDTO.getCityInfo());
    }
}
