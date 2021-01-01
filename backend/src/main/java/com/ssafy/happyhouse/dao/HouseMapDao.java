package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

@Mapper
public interface HouseMapDao {

List<SidoGugunCodeDto> getSido() throws Exception;
List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
List<HouseInfoDto> getDongInGugun(String gugun, int limit, int offset);
List<HouseInfoDto> getAptInDong(String dong) throws Exception;
List<HouseInfoDto> getAptInAptName(String AptName) throws Exception;
List<HouseInfoDto> getTotal() throws Exception;
List<HouseInfoDto> getAptInGugun(String gugun, int limit, int offset);
List<HouseInfoDto> getDealInAptName(String AptName) throws Exception;
}