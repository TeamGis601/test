package com.gis.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.gis.dto.FrequencyDto;
import com.gis.dto.GpsDto;
import com.gis.dto.LocalDto;
import com.gis.dto.NoiseDto;

@Mapper
public interface GisMapper {

    List<GpsDto> findAllGps();

    List<FrequencyDto> findAllFrequency();

    List<NoiseDto> findAllNoise();

    GpsDto findGpsByTime();

    FrequencyDto findFrequencyByTime();

    NoiseDto findNoiseByTime();

    void saveAllLocalData(LocalDto localDto);

    List<String> findCarNumByTempGpsTable();

    int countByGps();

    int countByFrequency();

    int countByNoise();

    List<GpsDto> findAllGpsByCarNum(String carNum);

    List<FrequencyDto> findAllFrequencyByCarNum(String carNum);

    List<NoiseDto> findAllNoiseByCarNum(String carNum);

}
