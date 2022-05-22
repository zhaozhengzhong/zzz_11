package cn.edu.zime.iot.zzz_11.config;


import cn.edu.zime.iot.zzz_11.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer
{
    @Override
    public  void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html");
    }
}
