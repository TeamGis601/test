package com.gis.mapper;


import java.time.LocalDate;
import java.time.LocalTime;
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
//@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GisMapperTest {

    @Autowired
    private GisMapper2 gisMapper;


    @Test
    @DisplayName("Mapper에서 각 테이블별 VO 테스트")
    void findVO(){

        //given
        List<String> carNumList = gisMapper.findCarNumByGpsTable();
        Gps gps = new Gps();
        Noise noise = new Noise();
        Frequency frequency = new Frequency();

        //when

        for (String car : carNumList) {

            gps = gisMapper.findGpsByCarNum(car);
            noise = gisMapper.findNoiseByCarNum(car);
            frequency = gisMapper.findFrequencyByCarNum(car);

            System.out.println("gps = " + gps);
            System.out.println("noise = " + noise);
            System.out.println("frequency = " + frequency +"\n");
        }

        //then
        Assertions.assertThat(gps.getCarNum()).isEqualTo(noise.getCarNum()).isEqualTo(frequency.getCarNum());
        Assertions.assertThat(gps.getDate()).isEqualTo(noise.getDate()).isEqualTo(frequency.getDate());

    }

    @Test
    @DisplayName("LocalDTO -> insert local_db table 테스트")
    void saveLocalDtoOnLocalDbTable(){

        LocalDto localDto = new LocalDto();

        localDto.setCarNum("103하2414");
        localDto.setDate(LocalDate.of(2023, 6, 1));
        localDto.setTime(LocalTime.of(6, 3, 20));
        localDto.setNoise(107L);
        localDto.setFrequency(1700L);
        localDto.setLon(127.0695955);
        localDto.setLat(37.29873072);
        localDto.setIsDone(true);

        gisMapper.saveSensorData(localDto);
    }
}
