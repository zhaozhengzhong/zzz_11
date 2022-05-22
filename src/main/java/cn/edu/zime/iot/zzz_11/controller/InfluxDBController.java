package cn.edu.zime.iot.zzz_11.controller;

import cn.edu.zime.iot.zzz_11.Model.TrackPoint;
import cn.edu.zime.iot.zzz_11.Model.User;
import cn.edu.zime.iot.zzz_11.service.InfluxDBService;
import cn.edu.zime.iot.zzz_11.util.GeneralResponse;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class InfluxDBController {

    @Autowired
    private InfluxDB influxDB;
    @Autowired
    private InfluxDBService influxDBService;

    @Value("${spring.influx.database}")
    private String database;

    @PostMapping("/addPosition")
    public  GeneralResponse add(@RequestParam String id, @RequestParam float lng, @RequestParam float lat){
        System.out.println("---开始插入数据---");
        TrackPoint trackPoint=new TrackPoint();
        trackPoint.setId(id);
        trackPoint.setLng(lng);
        trackPoint.setLat(lat);
        GeneralResponse response = new GeneralResponse();
        response.setSuccess(true);
        response.setData(influxDBService.addPosition(trackPoint));
        return response;
    }

    @GetMapping("/getPositions")
    public GeneralResponse getPositions() {
        GeneralResponse response =new GeneralResponse();
        response.setSuccess(true);
        List<TrackPoint> result=new ArrayList<>();
        result= influxDBService.getPositions();
        response.setData(result);
        return response;
    }

    @GetMapping("/getPosition")
    public GeneralResponse getPosition(@RequestParam int num) {
        GeneralResponse response =new GeneralResponse();
        response.setSuccess(true);
        List<TrackPoint> result=new ArrayList<>();
        result= influxDBService.getPosition(num);
        response.setData(result);
        return response;
    }
}

