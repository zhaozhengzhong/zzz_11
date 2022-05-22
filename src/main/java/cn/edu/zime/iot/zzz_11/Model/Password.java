package cn.edu.zime.iot.zzz_11.Model;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


@Component

public class Password {
    Map<Character, String> map=new HashMap<Character,String>();
    private String password;

    public void setPassword(String str) {
        this.password = getString(str);
    }

    public String getPassword() {
        return password;
    }

    public static String getString(String plainText) {
        byte[] mdBytes = null;
        try {
            mdBytes = MessageDigest.getInstance("MD5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法不存在！");
        }
        String mdCode = new BigInteger(1, mdBytes).toString(16);
        //得到16位加密字符

        if(mdCode.length() < 32) {
            int a = 32 - mdCode.length();
            for (int i = 0; i < a; i++) {
                mdCode = "0"+mdCode;
            }
        }
        return mdCode.toUpperCase(); //返回32位大写加密字符
//        return mdCode;            // 默认返回32位小写加密字符
    }




}
