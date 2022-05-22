package cn.edu.zime.iot.zzz_11.dao;


import cn.edu.zime.iot.zzz_11.Model.TrackPoint;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
@Resource

public class InfluxDBDao {
    @Autowired
    private InfluxDB influxDB;

    @Value("${spring.influx.database}")
    private String database;

    public int addPosition(TrackPoint trackPoint) {
        System.out.println("---开始插入数据---");
        influxDB.write(Point.measurement("student")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("id", trackPoint.getId())
                .addField("lng",trackPoint.getLng())
                .addField("lat", trackPoint.getLat())
                .build());
        return 1;
    }


    public List<TrackPoint> getPosition() {
        System.out.println("---开始查询数据---");
        String command = "select * from student";
        Query query = new Query(command, "students");
        QueryResult queryResult = influxDB.query(query, TimeUnit.MILLISECONDS);
        System.out.println(queryResult);
        List<QueryResult.Result> results = queryResult.getResults();
        if (results == null) {
            return null;
        }
        // 多个sql用分号隔开，因本次查询只有一个sql，所以取第一个就行
        QueryResult.Result result = results.get(0);
        List<QueryResult.Series> seriesList = result.getSeries();
        List<TrackPoint> trackPointList=new ArrayList<>();
        for (QueryResult.Series series : seriesList) {
            if (series == null) {
                return null;
            }
            System.out.println("结果数量为：" + (series.getValues() == null ?
                    0 : series.getValues().size()));
            System.out.println("columns ==>> " + series.getColumns());
            System.out.println("tags ==>> " + series.getTags());
            System.out.println("name ==>> " + series.getName());
//            System.out.println("values ==>> " + series.getValues());
            series.getValues().forEach(valueData -> {
                TrackPoint trackPoint = new TrackPoint();
                // 直接查询出来的是科学计数法，需要转换为Long类型的数据
//                System.out.println(valueData.get(0));
                BigDecimal decimalTime = new BigDecimal(valueData.get(0).toString());
//                String str=decimalTime.toString();
//                System.out.println(decimalTime.longValue());
                trackPoint.setTime(decimalTime.longValue());
                trackPoint.setId(valueData.get(1).toString());
                trackPoint.setLat(Float.valueOf(valueData.get(2).toString()));
                trackPoint.setLng(Float.valueOf(valueData.get(3).toString()));
//                System.out.println(trackPoint.toString());
                trackPointList.add(trackPoint);
            });
        }
        return trackPointList;
    }
}
