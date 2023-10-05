package com.gis.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.gis.dto.LocalDto;
import com.gis.vo.Frequency;
import com.gis.vo.Gps;
import com.gis.vo.Noise;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GisServiceTest {

    @Autowired
    private GisService2 gisService;

    @Test
    @DisplayName("SensorDto -> LocalDto로 통합하기")
    void combineSensorDto(){

        //given
        List<String> allCarNum = gisService.findAllCarNum();

        //when
        for (String car : allCarNum) {

            Gps gps = gisService.findGps(car);
            Frequency frequency = gisService.findFrequency(car);
            Noise noise = gisService.findNoise(car);

            LocalDto localDto = gisService.saveSensorOnLocalDto(car, gps, frequency, noise);

            System.out.println("localDto = " + localDto);

            //then
            Assertions.assertThat(localDto.getCarNum()).isEqualTo(car);
            Assertions.assertThat(localDto.getTime()).
                    isEqualTo(gisService.extractMostRecentTime(gps.getTime(), frequency.getTime(), noise.getTime()));
        }
    }
}
