package com.example.demo.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.service.AddressService;
import com.example.demo.service.StudentService;

@RestController
public class ExcleController {
	
	@Autowired
    private AddressService addressService;
	@Autowired
    private StudentService studentService;
	@RequestMapping(value = "AddressExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
      /*  HSSFSheet sheet2 = workbook.createSheet("信息表2");*/
        List<Address> classmateList = addressService.findAll();
        String fileName = "address"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;
        /*int rowNum2 = 1;*/
        String[] headers = { "AddressId", "AddressName"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
      /*  HSSFRow row2 = sheet2.createRow(0);*/
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
       /* for(int i=0;i<headers.length;i++){
            HSSFCell cell2 = row2.createCell(i);
            HSSFRichTextString text2 = new HSSFRichTextString(headers[i]);
            cell2.setCellValue(text2);
        }*/

        //在表中存放查询到的数据放入对应的列
        for (Address address : classmateList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(address.getAddressId());
            row1.createCell(1).setCellValue(address.getAddressName());
            rowNum++;
        }
       /* for (Address address2 : classmateList) {
            HSSFRow row21 = sheet2.createRow(rowNum2);
            row21.createCell(0).setCellValue(address2.getAddressId());
            row21.createCell(1).setCellValue(address2.getAddressName());
            rowNum2++;
        }*/

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
        System.out.println("导出excel成功~");
        
    }
	@RequestMapping(value = "StuList", method = RequestMethod.GET)
    public void StuList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "collegeId", required = false) String collegeId ,@RequestParam(value = "majorId", required = false) String majorId ,@RequestParam(value = "classId", required = false) String classId ,HttpServletResponse response,HttpSession session) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生表");
      /*  HSSFSheet sheet2 = workbook.createSheet("信息表2");*/
        Object id =  session.getAttribute("classId");
      	 String classId2 = id.toString();
      	 System.out.println("ids:"+classId2);
      	 if (nameLike == null) {
      		 nameLike = "";
   		}
      	 if (collegeId == null|| collegeId.equals("")) {
   			collegeId = "0";
   		}
      	 if (majorId == null|| majorId.equals("")) {
      		 majorId = "0";
   		}
      	 if (classId == null|| classId.equals("")) {
      		 classId = "0";
   		}
      	 if (classId2 != null &&  !classId2.equals("")) {
      		 classId = classId2;
   		}
   		List<Student> list = studentService.findByStudentPDF(nameLike, "", collegeId, majorId, classId);
        //List<Address> classmateList = addressService.findAll();
        String fileName = "StuList"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;
        /*int rowNum2 = 1;*/
        String[] headers = { "学生账号", "学生姓名","性别","专业","联系方式","邮箱","地址"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (Student student : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(student.getUsername());
            row1.createCell(1).setCellValue(student.getName());
            row1.createCell(2).setCellValue(student.getSex());
            row1.createCell(3).setCellValue(student.getMajorName());
            row1.createCell(4).setCellValue(student.getTelphone());
            row1.createCell(5).setCellValue(student.getMail());
            row1.createCell(6).setCellValue(student.getAddress());
            
            rowNum++;
        }
   

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
        System.out.println("导出excel成功~");
        
    }
}
