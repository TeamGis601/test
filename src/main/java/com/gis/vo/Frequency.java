package com.gis.vo;


import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Frequency {

    private String carNum;
    private LocalDate date;
    private LocalTime time;
    private Long frequency;
}
