package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.HouseMapDao;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

@Service
public class HouseMapServiceImpl implements HouseMapService {

    @Autowired
    HouseMapDao houseDao;

@Override
public List<SidoGugunCodeDto> getSido() throws Exception {
    return houseDao.getSido();
}

@Override
public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
    return houseDao.getGugunInSido(sido);
}

@Override
public List<HouseInfoDto> getDongInGugun(String gugun, int limit , int offset) throws Exception {
    return houseDao.getDongInGugun(gugun, limit, offset);
}

@Override
public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
    return houseDao.getAptInDong(dong);
}

@Override
public List<HouseInfoDto> getAptInAptName(String AptName) throws Exception {
    return houseDao.getAptInAptName(AptName);
}

@Override
public List<HouseInfoDto> getTotal() throws Exception {
    return houseDao.getTotal();
}

@Override
public List<HouseInfoDto> getAptInGugun(String gugun, int limit, int offset) throws Exception {

    return houseDao.getAptInGugun(gugun, limit, offset);
}

@Override
public List<HouseInfoDto> getDealInAptName(String AptName) throws Exception {
    return houseDao.getDealInAptName(AptName);
}
}