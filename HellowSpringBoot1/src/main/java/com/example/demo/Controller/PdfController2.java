package com.example.demo.Controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.service.AddressService;
import com.example.demo.util.PdfUtils;
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
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class PdfController2 {
	
	@Autowired
    private AddressService addressService;
	/*@Autowired
	private PdfUtils pdfUtils;*/
	@RequestMapping(value = "test2.pdf", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException, DocumentException {
		// 第一步，实例化一个document对象
		  Document document = new Document();
		  // 第二步，设置要到出的路径
		  //FileOutputStream out = new  FileOutputStream("H:/workbook111.pdf");
	      //如果是浏览器通过request请求需要在浏览器中输出则使用下面方式
	      OutputStream out = response.getOutputStream();
		  // 第三步,设置字符
         // BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
	      BaseFont bfChinese=BaseFont.createFont("C:\\Windows\\Fonts\\simkai.ttf", BaseFont.IDENTITY_H, false);    
		  Font fontZH = new Font(bfChinese, 12.0F, 0);
		  // 第四步，将pdf文件输出到磁盘
	      PdfWriter writer = PdfWriter.getInstance(document, out);
	      // 第五步，打开生成的pdf文件
	      document.open();
	      // 第六步,设置内容
	      String title = "地址";
	      document.add(new Paragraph(new Chunk(title, fontZH).setLocalDestination(title)));
	      document.add(new Paragraph("\n"));
	      // 创建table,注意这里的2是两列的意思,下面通过table.addCell添加的时候必须添加整行内容的所有列
	      PdfPTable table = new PdfPTable(2);
	      /*table.setWidthPercentage(100.0F);
	      table.setHeaderRows(1);
	      table.getDefaultCell().setHorizontalAlignment(1);*/
	      
	      /*table.addCell(new Paragraph("序号", fontZH));
	      table.addCell(new Paragraph("结果", fontZH));
	      table.addCell(new Paragraph("1", fontZH));
	      table.addCell(new Paragraph("出来了", fontZH));
	      table.addCell(new Paragraph("2", fontZH));
	      table.addCell(new Paragraph("出来了", fontZH));
	      table.addCell(new Paragraph("3", fontZH));
	      table.addCell(new Paragraph("出来了", fontZH));*/
	      List<Address> classmateList = addressService.findAll();
	      int rowNum = 1;
	    /*  for (Address address : classmateList) {
	            //HSSFRow row1 = sheet.createRow(rowNum);
	            row1.createCell(0).setCellValue(address.getAddressId());
	            row1.createCell(1).setCellValue(address.getAddressName());
	            rowNum++;
	        }*/
	      
	      
	      table.addCell(new Paragraph("结果", fontZH));
	      
	      document.add(table);
	      document.add(new Paragraph("\n"));
	      // 第七步，关闭document
	      document.close();
	      System.out.println("导出pdf成功~");
  
    }
	@RequestMapping(value = "test3.pdf", method = RequestMethod.GET)
	
	public void createPDF(HttpServletResponse response,String filename) throws IOException, DocumentException {
		
		filename = "D:\\pdf_table_002.pdf";
        Document document = new Document(PageSize.A4);
        OutputStream out = response.getOutputStream();
        try {
        	/*PdfWriter.getInstance(document, new FileOutputStream(filename));*/
        	PdfWriter.getInstance(document, out);
            document.addTitle("table of PDF");
            document.addAuthor("eff666");
            document.addSubject("This is the subject of the PDF file.");
            document.addKeywords("This is the keyword of the PDF file.");
            document.open();
            //PdfUtils pdfUtils = new PdfUtils();

            PdfPTable table = PdfUtils.createTable1();
            
            document.add(table);

            table = PdfUtils.createTable2();
            table.setSpacingBefore(5);
            table.setSpacingAfter(5);
            document.add(table);

            table = PdfUtils.createTable3();
            document.add(table);

            table = PdfUtils.createTable4();
            table.setSpacingBefore(5);
            table.setSpacingAfter(5);
            document.add(table);

            table = PdfUtils.createTable5();
            document.add(table);

            table = PdfUtils.createTable6();
            document.add(table);
            table = PdfUtils.createTable7();
            document.add(table);

        } 
        /*catch (FileNotFoundException e) {
            e.printStackTrace();
        } */
        catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
            System.out.println("导出pdf成功~");
            
        }
        
    
	}
	
	@RequestMapping(value = "test.pdf", method = RequestMethod.GET)
	
	public void createPDF2(HttpServletResponse response,String filename) throws IOException, DocumentException {
		
		filename = "D:\\pdf_table_002.pdf";
        Document document = new Document(PageSize.A4);
        OutputStream out = response.getOutputStream();
        List<Address> classmateList = addressService.findAll();
        try {
        	/*PdfWriter.getInstance(document, new FileOutputStream(filename));*/
        	PdfWriter.getInstance(document, out);
            document.addTitle("table of PDF");
            document.addAuthor("eff666");
            document.addSubject("This is the subject of the PDF file.");
            document.addKeywords("This is the keyword of the PDF file.");
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
            System.out.println("导出pdf成功~");
            
        }
        
    
	}
	
	
	
	
}
