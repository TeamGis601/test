package com.gis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gis.dto.FrequencyDto;
import com.gis.dto.GpsDto;
import com.gis.dto.LocalDto;
import com.gis.dto.NoiseDto;
import com.gis.vo.Frequency;
import com.gis.vo.Gps;
import com.gis.vo.Noise;

@Mapper
public interface GisMapper2 {

    // save Data on Temp Table(DTO)
    void saveGpsOnTempGps(GpsDto gpsDto);
    void saveNoiseOnTempGps(NoiseDto noiseDto);
    void saveFrequencyOnTempGps(FrequencyDto frequencyDto);


    // find Data By Temp Table(VO)
    List<String> findCarNumByGpsTable();
    Gps findGpsByCarNum(String carNum);
    Noise findNoiseByCarNum(String carNum);
    Frequency findFrequencyByCarNum(String carNum);

    // save localDto on local_db
    void saveSensorData(LocalDto localDto);


    // delete all temp_table data
    void deleteAllTempGps();
    void deleteAllTempNoise();
    void deleteAllTempFrequency();
}
