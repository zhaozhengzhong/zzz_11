package cn.edu.zime.iot.zzz_11.Model;


import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;

@Component
@Measurement(name = "student")

public class TrackPoint implements Comparable
{
    @Column(name="id", tag = true)
    private String id;
    @Column(name="lng",tag= true)
    private float lng;
    @Column(name="lat",tag= true)
    private float lat;
    @Column(name="time")
    private long time;

    @Override
    public String toString() {
        return "TrackPoint{" +
                "id='" + id + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", time=" + time +
                '}';
    }




    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    @Override
    public int compareTo(Object o) {
        TrackPoint s = (TrackPoint) o;
        if (this.lng > s.lng) {
            return 1;
        }
        else if (this.lng < s.lng) {
            return -1;
        }
        else {
            if (this.lat >= s.lat) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }

}


