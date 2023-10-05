package com.gis.service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gis.dto.FrequencyDto;
import com.gis.dto.GpsDto;
import com.gis.dto.LocalDto;
import com.gis.dto.NoiseDto;
import com.gis.mapper.GisMapper2;
import com.gis.vo.Frequency;
import com.gis.vo.Gps;
import com.gis.vo.Noise;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GisService2 {

    private final static Long MIN_NOISE = 80L;
    private final static Long MIN_FREQUENCY = 1500L;

    private final GisMapper2 gisMapper;

    public void saveTempGps(GpsDto gpsDto) {

        gisMapper.saveGpsOnTempGps(gpsDto);
    }

    public void saveTempNoise(NoiseDto noiseDto) {
        gisMapper.saveNoiseOnTempGps(noiseDto);
    }

    public void saveTempFrequency(FrequencyDto frequencyDto) {
        gisMapper.saveFrequencyOnTempGps(frequencyDto);
    }

    public void deleteAllTempTable(){

        gisMapper.deleteAllTempGps();
        gisMapper.deleteAllTempNoise();
        gisMapper.deleteAllTempFrequency();

        log.info("========= DELETE ALL TEMP_TABLE DATA =========");
    }

    public List<String> findAllCarNum(){
        return gisMapper.findCarNumByGpsTable();
    }

    public Gps findGps(String car){
        return gisMapper.findGpsByCarNum(car);
    }

    public Noise findNoise(String car) {
        return gisMapper.findNoiseByCarNum(car);
    }

    public Frequency findFrequency(String car) {
        return gisMapper.findFrequencyByCarNum(car);
    }

    public void saveAllSensor(LocalDto localDto) {
        gisMapper.saveSensorData(localDto);
    }

    public LocalDto saveSensorOnLocalDto(String car, Gps gps, Frequency frequency, Noise noise){

        LocalTime mostRecentTime = extractMostRecentTime(gps.getTime(), frequency.getTime(), noise.getTime());
        LocalDto localDto = new LocalDto();

        localDto.setCarNum(car);
        localDto.setDate(gps.getDate());
        localDto.setTime(mostRecentTime);
        localDto.setNoise(noise.getNoise());
        localDto.setFrequency(frequency.getFrequency());
        localDto.setLon(gps.getLon());
        localDto.setLat(gps.getLat());
        localDto.setIsDone(isClean(noise.getNoise(), frequency.getFrequency()));

        return localDto;
    }

    public LocalTime extractMostRecentTime(LocalTime gpsTime, LocalTime frequencyTime, LocalTime noiseTime){

        List<LocalTime> timeList = Arrays.asList(gpsTime, frequencyTime, noiseTime);
        LocalTime mostRecentTime = Collections.max(timeList);

        log.info("================================================");
        log.info("Most Recent Time = [{}]", mostRecentTime);

        return mostRecentTime;
    }

    public Boolean isClean(Long noise, Long frequency){

        return noise >= MIN_NOISE && frequency >= MIN_FREQUENCY;
    }


}
