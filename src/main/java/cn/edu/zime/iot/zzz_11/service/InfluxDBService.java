package cn.edu.zime.iot.zzz_11.service;

import cn.edu.zime.iot.zzz_11.Model.TrackPoint;
import cn.edu.zime.iot.zzz_11.Model.User;
import cn.edu.zime.iot.zzz_11.dao.InfluxDBDao;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.abs;

@Service
@Resource
public class InfluxDBService {
    @Autowired
    InfluxDBDao influxDBDao;
    public int addPosition(TrackPoint trackPoint)
    {
        return influxDBDao.addPosition(trackPoint);
    }

    public List<TrackPoint> getPositions()
    {
        return influxDBDao.getPosition();
    }

    public List<TrackPoint> getPosition(int res)
    {
      List<TrackPoint>t=influxDBDao.getPosition();
      List<TrackPoint> result=new ArrayList<>();
        Collections.sort(t);
        int r=0;
        int cnt=0;
        int num[]=new int[105];
//        System.out.println("res:"+res+"     "+t.size());
        int len=t.size();
        for(int i=0;i<len;i++)
        {
            TrackPoint trackPoint=t.get(i);
            int f=0;

            while(r<t.size()&&abs(t.get(r).getLat()-trackPoint.getLat())<=0.1&&abs(t.get(r).getLng()-trackPoint.getLng())<=0.1)
            {
//                System.out.println("cnt:"+cnt+"     i:"+i+"     r:"+r);
                num[Integer.valueOf(t.get(r).getId())]++;
                if(num[Integer.valueOf(t.get(r).getId())]==1)
                {
                    cnt++;
                }
                r++;
            }
            if(cnt>=res)
            {
                trackPoint.setId(String.valueOf(cnt));
                result.add(trackPoint);
                i=r-1;
                Arrays.fill(num,0);
                cnt=0;
                f=1;
            }
            if(f==0)
            {
                num[Integer.valueOf(trackPoint.getId())]--;
                if(num[Integer.valueOf(trackPoint.getId())]==0)
                {
                    cnt--;
                }
            }
        }
        for(int i=0;i<result.size();i++)
        {
            System.out.println(result.toString());
        }
        return result;
    }
}


