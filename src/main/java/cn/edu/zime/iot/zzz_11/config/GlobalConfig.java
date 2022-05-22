package cn.edu.zime.iot.zzz_11.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
@ConfigurationProperties(prefix = GlobalConfig.PREFIX)
public class GlobalConfig
{
    public static final String PREFIX = "test";
    private int power;
    private int id;


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
