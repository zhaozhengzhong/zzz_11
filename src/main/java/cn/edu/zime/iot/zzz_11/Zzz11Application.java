package cn.edu.zime.iot.zzz_11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
public class Zzz11Application {

	public static void main(String[] args) {
		SpringApplication.run(Zzz11Application.class, args);
	}

}
