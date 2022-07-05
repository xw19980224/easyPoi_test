package com.xw.easypoi_test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import com.google.common.collect.Lists;
import com.xw.easypoi_test.model.entity.MsgClient;
import com.xw.easypoi_test.model.entity.Student;
//import com.xw.easypoi_test.rep.StudentDao;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class EasyPoiTestApplicationTests {

//    @Autowired
//    private StudentDao studentDao;

    @Test
    public void test() {
        for (int i = 1; i <= 1000000; i++) {
            Student student = new Student();
            student.setName("账号:" + i);
            student.setAge(i);
            student.setAddress(String.format("上海市浦东新区北蔡镇高青路%s弄", i));
            student.setSex(i % 2 == 0 ? "女" : "男");
            System.out.println(student.toString());
//            studentDao.save(student);
        }
    }

}
