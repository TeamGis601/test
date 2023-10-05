package com.gis.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gis.dto.SensorDto;
import com.gis.mapper.GisMapper2;
import com.gis.service.GisScheduleService;
import com.gis.service.GisService2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GisRestController {

    private final GisMapper2 gisMapper;
    private final GisService2 gisService;
    private final GisScheduleService scheduleService;

    // 2초마다 데이터요청
    @PostMapping("/api/sensor/save")
    public String processSensorData(@RequestBody SensorDto sensorData){

        log.info("sensorData = {}", sensorData);

        scheduleService.startScheduler();

        gisService.saveTempGps(sensorData.getGpsDto());
        gisService.saveTempNoise(sensorData.getNoiseDto());
        gisService.saveTempFrequency(sensorData.getFrequencyDto());


        return "ok";
    }

//    @GetMapping("/api/sensor/start")
//    public String sensorRecordStart() {
//
//        scheduleService.startScheduler();
//
//        return "ok";
//
//    }

    @PostMapping("/api/sensor/stop")
    public String sensorRecordStop() {

        scheduleService.stopScheduler();

        return "ok";
    }

    @PostMapping("/api/sensor/cycle/{second}")
    public String changeCleanCycle(@PathVariable String second){

        scheduleService.stopScheduler();
//        String cron = "* 0/"+ minute +" * * * *";
        String cron = "0/"+second+" * * * * *";

        scheduleService.changeCronSet(cron);
        scheduleService.startScheduler();

        return "ok";

    }



}
