package com.gis.vo;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Noise {

    private String carNum;
    private LocalDate date;
    private LocalTime time;
    private Long noise;
}
