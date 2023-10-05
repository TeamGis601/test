package com.gis.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class NoiseDto {

    private String carNum;
    private LocalDate date;
    private LocalTime time;
    private Long noise;

}
