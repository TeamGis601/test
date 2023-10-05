package com.gis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GpsDto {

    private String carNum;
    private LocalDate date;
    private LocalTime time;
    private Double lon;
    private Double lat;

}
