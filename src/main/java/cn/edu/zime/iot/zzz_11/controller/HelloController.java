//package cn.edu.zime.iot.zzz_11.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class HelloController {
//    @Value("${iot.server:port}")
//    String port;
//    @PostMapping("/save")
//    public String saveName(String name, HttpSession session)
//    {
//        session.setAttribute("name",name);
//        return port;
//    }
//
//    @GetMapping("/get")
//    public String getName(HttpSession session)
//    {
//        return port+":"+session.getAttribute("name").toString();
//    }
//}
