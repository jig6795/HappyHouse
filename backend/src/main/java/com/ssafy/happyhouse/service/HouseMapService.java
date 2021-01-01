package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseMapService {

    List<SidoGugunCodeDto> getSido() throws Exception;
    List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
    List<HouseInfoDto> getDongInGugun(String gugun , int limit , int offset) throws Exception;
    List<HouseInfoDto> getAptInGugun(String gugun , int limit , int offset) throws Exception;
    List<HouseInfoDto> getAptInDong(String dong) throws Exception;
    List<HouseInfoDto> getAptInAptName(String AptName) throws Exception;
    List<HouseInfoDto> getTotal() throws Exception;
    List<HouseInfoDto> getDealInAptName(String AptName) throws Exception;
}