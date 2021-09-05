package com.example.demo.Controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.service.AddressService;
import com.example.demo.service.CenterService;
import com.example.demo.service.StudentService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class PdfController {
	
	@Autowired
    private AddressService addressService;
	@Autowired
    private CenterService centerService;
	@Autowired
    private StudentService studentService;
	@RequestMapping(value = "AddressPdfDownloads.pdf", method = RequestMethod.GET)
public void createPDF(HttpServletResponse response,String filename) throws IOException, DocumentException {
		
		//filename = "D:\\pdf_table_002.pdf";
        Document document = new Document(PageSize.A4);
        //OutputStream out = response.getOutputStream();
        List<Address> classmateList = addressService.findAll();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
        	/*PdfWriter.getInstance(document, new FileOutputStream(filename));*/
        	PdfWriter.getInstance(document, buffer);
            document.addTitle("table of PDF");
//            document.addAuthor("eff666");
//            document.addSubject("This is the subject of the PDF file.");
//            document.addKeywords("This is the keyword of the PDF file.");
            document.open();
            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(595);
            //table.setLockedWidth(true);
            BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
    		Font font = new Font(bfChinese, 12,Font.NORMAL);

            PdfPCell cell = new PdfPCell();
            cell = new PdfPCell(new Paragraph("测试",font));
            cell.setColspan(2);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("地址ID"),font));
            cell.setColspan(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("地址名称"),font));
            cell.setColspan(1);
            table.addCell(cell);
            
           /* for (int i = 1; i < 101; i++) {
                cell = new PdfPCell(new Phrase(String.valueOf(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            }*/
            for (Address address : classmateList) {
            	 cell = new PdfPCell(new Phrase(String.valueOf(address.getAddressId())));
            	 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
            	 cell = new PdfPCell(new Phrase(String.valueOf(address.getAddressName()),font));
                 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
            }
            
            document.add(table);

        } 
        /*catch (FileNotFoundException e) {
            e.printStackTrace();
        } */
        catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
            //System.out.println("导出pdf成功~");
            
        }
        //document.close();
        //System.out.println("导出pdf成功~");
        PdfReader reader = new PdfReader(buffer.toByteArray());
		PdfStamper stamp = new PdfStamper(reader, buffer);
		stamp.close();
		buffer.flush();
		//this.getResponse().setContentType("application/pdf");
		ServletOutputStream out = response.getOutputStream();
		//OutputStream out = response.getOutputStream();
		buffer.writeTo(out);
		out.flush();
		out.close();
        
    
	}
	@RequestMapping(value = "StuList.pdf", method = RequestMethod.GET)
	public void StuList(@RequestParam(value = "nameLike", required = false) String nameLike ,@RequestParam(value = "collegeId", required = false) String collegeId ,@RequestParam(value = "majorId", required = false) String majorId ,@RequestParam(value = "classId", required = false) String classId ,HttpServletResponse response,String filename,HttpSession session) throws IOException, DocumentException {
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
		Document document = new Document(PageSize.A4);
        //OutputStream out = response.getOutputStream();
       // List<Address> classmateList = addressService.findAll();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
        	/*PdfWriter.getInstance(document, new FileOutputStream(filename));*/
        	PdfWriter.getInstance(document, buffer);
            document.addTitle("table of PDF");
//            document.addAuthor("eff666");
//            document.addSubject("This is the subject of the PDF file.");
//            document.addKeywords("This is the keyword of the PDF file.");
            document.open();
            PdfPTable table = new PdfPTable(7);
            table.setTotalWidth(595);
            //table.setLockedWidth(true);
            BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
    		Font font = new Font(bfChinese, 12,Font.NORMAL);

            PdfPCell cell = new PdfPCell();
            cell = new PdfPCell(new Paragraph("学生名单",font));
            cell.setColspan(7);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("学生账号"),font));
            cell.setColspan(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("学生姓名"),font));
            cell.setColspan(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("性别"),font));
            cell.setColspan(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("专业"),font));
            cell.setColspan(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("联系方式"),font));
            cell.setColspan(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("邮箱"),font));
            cell.setColspan(1);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.valueOf("地址"),font));
            cell.setColspan(1);
            table.addCell(cell);
            
           /* for (int i = 1; i < 101; i++) {
                cell = new PdfPCell(new Phrase(String.valueOf(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            }*/
            for (Student student : list) {
            	 cell = new PdfPCell(new Phrase(String.valueOf(student.getUsername())));
            	 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
            	 cell = new PdfPCell(new Phrase(String.valueOf(student.getName()),font));
                 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
                 cell = new PdfPCell(new Phrase(String.valueOf(student.getSex()),font));
                 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
                 cell = new PdfPCell(new Phrase(String.valueOf(student.getMajorName()),font));
                 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
                 cell = new PdfPCell(new Phrase(String.valueOf(student.getTelphone()),font));
                 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
                 cell = new PdfPCell(new Phrase(String.valueOf(student.getMail()),font));
                 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
                 cell = new PdfPCell(new Phrase(String.valueOf(student.getAddress()),font));
                 cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                 table.addCell(cell);
            }
            
            document.add(table);

        } 
        /*catch (FileNotFoundException e) {
            e.printStackTrace();
        } */
        catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
            //System.out.println("导出pdf成功~");
            
        }
        //document.close();
        //System.out.println("导出pdf成功~");
        PdfReader reader = new PdfReader(buffer.toByteArray());
		PdfStamper stamp = new PdfStamper(reader, buffer);
		stamp.close();
		buffer.flush();
		//this.getResponse().setContentType("application/pdf");
		ServletOutputStream out = response.getOutputStream();
		//OutputStream out = response.getOutputStream();
		buffer.writeTo(out);
		out.flush();
		out.close();
	}

}
