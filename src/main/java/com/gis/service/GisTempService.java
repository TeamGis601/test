package com.gis.service;

import org.springframework.stereotype.Service;
import com.gis.mapper.GisMapper2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GisTempService {

    private final GisMapper2 gisMapper;


}
