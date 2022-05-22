package cn.edu.zime.iot.zzz_11.dao;

import cn.edu.zime.iot.zzz_11.Model.Password;
import cn.edu.zime.iot.zzz_11.Model.User;
import cn.edu.zime.iot.zzz_11.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Resource


public class UserDao {
    @Autowired
    GlobalConfig globalConfig;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<User> users;

    public boolean verify(User user)
    {
        users=getAll();
        Password p=new Password();
        p.setPassword(user.getPassword());
        for (User u:users)
        {
//            System.out.println("u.name:"+u.getUsername()+"     u.pa:"+u.getPassword()+"     user.name:"+user.getUsername()+"      user.pw:"+p.getPassword()+"      "+u.getUsername().equals(user.getUsername()));
            if(u.getUsername().equals(user.getUsername())&& p.getPassword().equals(u.getPassword()))
            {
                globalConfig.setPower(u.getPower());
//                System.out.println("power:"+ globalConfig.getPower());
                globalConfig.setId(u.getId());
                return true;
            }
        }

        return false;
    }
    public List<User> getAll()
    {
//        System.out.println(jdbcTemplate.query("select * from users",new BeanPropertyRowMapper<>(User.class)).toString());
        return jdbcTemplate.query("select * from user",new BeanPropertyRowMapper<>(User.class));
    }

    public int addUser(User user)
    {
        Password p=new Password();
        p.setPassword(user.getPassword());
        return jdbcTemplate.update("insert into user(username,password,power) values(?,?,?)",user.getUsername(), p.getPassword(),user.getPower());
    }

    public int updateUser(User user)
    {
        Password p=new Password();
        p.setPassword(user.getPassword());
        System.out.println("username:"+user.getUsername()+"   password:"+user.getPassword()+"    power:"+user.getPower()+"     id:"+user.getId());
         return jdbcTemplate.update("update user set username=?,password=?,power=? WHERE id=?"
            ,user.getUsername(), p.getPassword(),user.getPower(),user.getId());
    }

    public int deleteUserById(int id)
    {
        return jdbcTemplate.update("delete from user where  id=?",id);
    }

    public User getUserById(int id)
    {
        return jdbcTemplate.queryForObject("select * from user where id=?",new BeanPropertyRowMapper<>(User.class),id);
    }

}
