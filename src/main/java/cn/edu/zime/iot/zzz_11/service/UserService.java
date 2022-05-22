package cn.edu.zime.iot.zzz_11.service;

import cn.edu.zime.iot.zzz_11.Model.Password;
import cn.edu.zime.iot.zzz_11.Model.User;
import cn.edu.zime.iot.zzz_11.dao.UserDao;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Resource

public class UserService {
    @Autowired
    UserDao userDao;


    public int addUser(User user)
    {
        return userDao.addUser(user);
    }

    public int updateUser(User user)
    {
        return userDao.updateUser(user);
    }

    public int deleteUserById(int id)
    {
        return userDao.deleteUserById(id);
    }

    public User getUserById(int id)
    {
        return userDao.getUserById(id);
    }

    public List<User> getAllUser()
    {
        return userDao.getAll();
    }
    public int adduserByExcel(MultipartFile file) throws IOException {
        int count=0;
        List<User> list=new ArrayList<>();
        Workbook wb=null;
        String filename=file.getOriginalFilename();
        String suffix=filename.substring(filename.lastIndexOf(".")+1);
        System.out.println(filename);
        //把文件转换为输入流
        InputStream inputStream=file.getInputStream();
        //判断是否是xlsx后缀
        if(suffix.equals("xlsx")){
            wb=new XSSFWorkbook(inputStream);
        }else {
            wb=new HSSFWorkbook(inputStream);
        }
        //获得第一张sheet表
        Sheet sheet=wb.getSheetAt(0);
        if(sheet!=null){
            int x=sheet.getLastRowNum();
            int line=1;
            //数据是从第三行开始，所以这里从第三行开始遍历
            for (;line<=x;line++){

                User user=new User();
                Row row=sheet.getRow(line);
                if(row==null)
                {

                    continue;
                }
//                System.out.println("line"+line+"     get:"+row.getCell(0));
                //判断单元格类型是否为文本类型
                if(null == row.getCell(0)){
                    continue;
                }
                Cell cell=row.getCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                System.out.println(cell.getStringCellValue());
                user.setUsername(cell.getStringCellValue());
                if(null == row.getCell(1)){
                    continue;
                }
                cell=row.getCell(1);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                user.setPassword(cell.getStringCellValue());
//                System.out.println(row.getCell(1).getStringCellValue());
                if(null == row.getCell(2)){
                    continue;
                }
                cell=row.getCell(2);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                user.setPower(Integer.valueOf(cell.getStringCellValue()));
//                System.out.println(cell.getStringCellValue());
                list.add(user);
            }
        }
        //在这我是用mybatis把得到的数据集合插入了数据库
        for (User user:list){
            userDao.addUser(user);
        }
        //返回的是插入了多少行
        return count;
    }

}
