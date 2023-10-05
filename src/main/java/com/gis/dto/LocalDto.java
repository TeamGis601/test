package com.gis.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class LocalDto {

    private Long gid;
    private String carNum;
    private LocalDate date;
    private LocalTime time;
    private Long noise;
    private Long frequency;
    private Double lon;
    private Double lat;
    private String geom;
    private Boolean isDone;

}
