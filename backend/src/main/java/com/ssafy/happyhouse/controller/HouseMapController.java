package com.ssafy.happyhouse.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseMapService;

@Controller
public class HouseMapController {

    @Autowired
    HouseMapService service;

    @RequestMapping("/map")
    public void map(HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setCharacterEncoding("UTF-8");
    
    String strLimit = request.getParameter("limit");
    int limit  = 10;
    if(strLimit!=null) {
        
    limit = Integer.parseInt(strLimit);
    }
    String strOffset = request.getParameter("offset");
    int offset = 0;
    if(strOffset!=null) {
        offset = Integer.parseInt(strOffset);
    }
    
//        System.out.println(limit+ " "+ offset);
        String act = request.getParameter("act");
//        System.out.println(act+"1");
        if("sido".equals(act)) {
            PrintWriter out = response.getWriter();
            List<SidoGugunCodeDto> list = null;
            JSONArray arr = new JSONArray();
            //System.out.println("sido");
            try {
                list = service.getSido();
                for(SidoGugunCodeDto dto : list) {
                    JSONObject obj = new JSONObject(); // { }
                    obj.put("sido_code", dto.getSidoCode()); // {'sido_code':'11'}
                    obj.put("sido_name", dto.getSidoName()); // {'sido_code':'11','sido_name':'서울특별시'}
                    arr.add(obj); // [{'sido_code':'11','sido_name':'서울특별시'}, ...]
                }
            } catch (Exception e) {
                arr = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("message_code", "-1");
                arr.add(obj);
                e.printStackTrace();
            } finally {
                out.print(arr.toJSONString());
                out.close();
            }
        }//sido
        else if("gugun".equals(act)) {
            String sido = request.getParameter("sido"); // 11
//            System.out.println("sidocode in gugun  "+sido);
            PrintWriter out = response.getWriter();
            List<SidoGugunCodeDto> list = null;
            JSONArray arr = new JSONArray();
            try {
                list = service.getGugunInSido(sido.substring(0, 2));
                for(SidoGugunCodeDto dto : list) {
                    JSONObject obj = new JSONObject();
                    obj.put("gugun_code", dto.getGugunCode());
                    obj.put("gugun_name", dto.getGugunName());
                    arr.add(obj);
                }
            } catch (Exception e) {
                arr = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("message_code", "-1");
                arr.add(obj);
                e.printStackTrace();
            } finally {
                out.print(arr.toJSONString());
                out.close();
            }
        }//gugun
        else if("dong".equals(act)) {
            String gugun = request.getParameter("gugun");
//            System.out.println("dong"+gugun);
            PrintWriter out = response.getWriter();
            List<HouseInfoDto> list = null;
            JSONArray arr = new JSONArray();
            try {
                list = service.getDongInGugun(gugun.substring(0, 5), limit, offset);
                for(HouseInfoDto dto : list) {
                    JSONObject obj = new JSONObject();
                    obj.put("code", dto.getCode());
                    obj.put("dong", dto.getDong());
                    arr.add(obj);
                }
            } catch (Exception e) {
                arr = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("message_code", "-1");
                arr.add(obj);
                e.printStackTrace();
            } finally {
                out.print(arr.toJSONString());
                out.close();
            }
        }//dong
        else if("dong2".equals(act)) {
            String gugun = request.getParameter("gugun");
            PrintWriter out = response.getWriter();
            List<HouseInfoDto> list = null;
            JSONArray arr = new JSONArray();
            try {
//                System.out.println("리스트...");
                list = service.getAptInGugun(gugun.substring(0, 5), limit, offset);
                for(HouseInfoDto dto : list) {
                    JSONObject obj = new JSONObject();
//                    System.out.println("=========="+dto.getAptName());
                    obj.put("AptName", dto.getAptName());
                    arr.add(obj);
                }
            } catch (Exception e) {
                arr = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("message_code", "-1");
                arr.add(obj);
                e.printStackTrace();
            } finally {
                out.print(arr.toJSONString());
                out.close();
            }
        }//dong
        else if("totalDong".equals(act)) {
            String gugun = request.getParameter("gugun");
//            System.out.println("totalDong"+" "+gugun.substring(0, 5));
            PrintWriter out = response.getWriter();
            List<HouseInfoDto> list = null;
            List<HouseInfoDto> totalList = null;
            List<String> gugunList = new ArrayList<String>();
            JSONArray arr = new JSONArray();
            try {
                list = service.getDongInGugun(gugun.substring(0, 5),limit,offset);
                for(HouseInfoDto dto : list) {
                    gugunList.add(dto.getDong());
                }
                for (String s : gugunList) {
                    totalList = service.getAptInDong(s);
                    for(HouseInfoDto dto : totalList) {
                        JSONObject obj = new JSONObject();
                        obj.put("no", dto.getNo());
                        obj.put("dong", dto.getDong());
                        obj.put("aptName", dto.getAptName());
                        obj.put("code", dto.getCode());
                        obj.put("jibun", dto.getJibun());
                        obj.put("lat", dto.getLat());
                        obj.put("lng", dto.getLng());
                        arr.add(obj);
                    }
                    totalList.clear();
                }
            } catch (Exception e) {
                arr = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("message_code", "-1");
                arr.add(obj);
                e.printStackTrace();
            } finally {
                out.print(arr.toJSONString());
                out.close();
            }
        }//dong
        else if("apt".equals(act)) {
            String dong = request.getParameter("dong");
            PrintWriter out = response.getWriter();
            List<HouseInfoDto> list = null;
            JSONArray arr = new JSONArray();
            try {
                list = service.getAptInDong(dong);
                for(HouseInfoDto dto : list) {
                    JSONObject obj = new JSONObject();
                    obj.put("no", dto.getNo());
                    obj.put("dong", dto.getDong());
                    obj.put("aptName", dto.getAptName());
                    obj.put("code", dto.getCode());
                    obj.put("jibun", dto.getJibun());
                    obj.put("lat", dto.getLat());
                    obj.put("lng", dto.getLng());
                    arr.add(obj);
                }
            } catch (Exception e) {
                arr = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("message_code", "-1");
                arr.add(obj);
                e.printStackTrace();
            } finally {
                out.print(arr.toJSONString());
                out.close();
            }
        }//dong
        else if("AptName".equals(act)) {
            String AptName = request.getParameter("AptName");
            PrintWriter out = response.getWriter();
//            System.out.println("AptName"+AptName);
            List<HouseInfoDto> list = null;
            JSONArray arr = new JSONArray();
            try {
                list = service.getAptInAptName(AptName);
                for(HouseInfoDto dto : list) {
                    JSONObject obj = new JSONObject();
                    obj.put("no", dto.getNo());
                    obj.put("dong", dto.getDong());
                    obj.put("AptName", dto.getAptName());
                    obj.put("code", dto.getCode());
                    obj.put("jibun", dto.getJibun());
                    obj.put("lat", dto.getLat());
                    obj.put("lng", dto.getLng());
                    arr.add(obj);
                }
            } catch (Exception e) {
                arr = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("message_code", "-1");
                arr.add(obj);
                e.printStackTrace();
            } finally {
                out.print(arr.toJSONString());
                out.close();
            }
        }//dong
        else if("Deal".equals(act)) {
            String AptName = request.getParameter("AptName");
            PrintWriter out = response.getWriter();
//            System.out.println("AptName"+AptName);
            List<HouseInfoDto> list = null;
            JSONArray arr = new JSONArray();
            try {
                list = service.getDealInAptName(AptName);
                for(HouseInfoDto dto : list) {
                    JSONObject obj = new JSONObject();
                    obj.put("deal", dto.getDealAmount());
                    arr.add(obj);
                }
            } catch (Exception e) {
                arr = new JSONArray();
                JSONObject obj = new JSONObject();
                obj.put("message_code", "-1");
                arr.add(obj);
                e.printStackTrace();
            } finally {
                out.print(arr.toJSONString());
                out.close();
            }
        }//dong

    }//process

}