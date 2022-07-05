package com.xw.easypoi_test.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import com.xw.easypoi_test.model.entity.Student;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Test
 * @Author: MaxWell
 * @Description:
 * @Date: 2022/7/4 14:22
 * @Version: 1.0
 */

@Slf4j
@RequestMapping("/test")
@RestController
public class Test {

//    @Autowired
//    private StudentDao studentDao;


    @GetMapping(value = "/export", produces = "application/vnd.ms-excel")
    public void export(@RequestParam("size") Integer size, HttpServletResponse response) {
        //大前提，很重要！！假设导出的Excel数据是List<A> aList
        //下面代码，我类 统一用A代替,需要导出的原数据用aList代替
        List<Student> list = getStudents(size);

        ExportParams params = new ExportParams("邀请关系表", "邀请关系表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Student.class, list);
        ServletOutputStream out = null;
        try {
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("邀请关系表.xlsx", "UTF-8"));
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            out = response.getOutputStream();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    private List<Student> getStudents(Integer size) {
        List<Student> list = Lists.newArrayList();
        for (int i = 1; i <= size; i++) {
            Student student = new Student();
            student.setId(i + "");
            student.setName("账号:" + i);
            student.setAge(i);
            student.setAddress(String.format("上海市浦东新区北蔡镇高青路%s弄", i));
            student.setSex(i % 2 == 0 ? "女" : "男");
            System.out.println(student.toString());
            list.add(student);
        }
        return list;
    }

    @RequestMapping(value = "/exportOrderList", method = RequestMethod.GET)
    public void exportOrderList(ModelMap map,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        List<Student> students = getStudents(1000000);
        ExportParams params = new ExportParams("圈选人群导出", "人群列表", ExcelType.XSSF);
        //导出时排除一些字段
        params.setExclusions(new String[]{});
        map.put(NormalExcelConstants.DATA_LIST, students);
        map.put(NormalExcelConstants.CLASS, Student.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "students");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

}
