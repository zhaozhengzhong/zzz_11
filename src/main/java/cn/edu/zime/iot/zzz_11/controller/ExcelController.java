package cn.edu.zime.iot.zzz_11.controller;

import cn.edu.zime.iot.zzz_11.Model.User;
import cn.edu.zime.iot.zzz_11.service.UserService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller


public class ExcelController {
    @Autowired
    private UserService userService;

    @GetMapping("/download")
    public void usersExcel(HttpServletResponse response) {
        XSSFWorkbook wb = show();
        String fileName = "用户信息.xlsx";
        OutputStream os = null;
        try {
            //解决中文乱码
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //将查询结果导出到excel，需要修改ContentType请求信息格式--application/vnd.ms-excel
            response.setContentType("application/vnd.ms-excel");
            //Content-disposition 提供打开/保存的对话框   attachment--作为附件下载
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            //获取输出流
            os = response.getOutputStream();
            //将整理好的excel数据写入流中
            wb.write(os);
            //清空缓冲区数据
            os.flush();
            //关闭读写流
            os.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public XSSFWorkbook show() {
        List<User> list=userService.getAllUser();
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Users");//创建一张表
        Row titleRow = sheet.createRow(0);//创建第一行 起始为0
        titleRow.createCell(0).setCellValue("序号");//第一列
        titleRow.createCell(1).setCellValue("用户名");
        titleRow.createCell(2).setCellValue("密码");
        titleRow.createCell(3).setCellValue("权限");
        int rowNum = 1;
        for (User user : list) {
            Row row = sheet.createRow(rowNum);//row 从1开始 即第二行开始存数据
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUsername());
            row.createCell(2).setCellValue(user.getPassword());
            row.createCell(3).setCellValue(user.getPower());
            rowNum++;
        }
        return wb;
    }

    @PostMapping("/upload")
    public String upload(MultipartFile uploadFile) throws Exception {
        int count= userService.adduserByExcel(uploadFile);
        return "users";
    }

}
