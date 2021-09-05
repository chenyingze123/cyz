package com.example.demo.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.service.AddressService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class ExcelToPdfController {
	@Autowired
	private AddressService addressService;

	@RequestMapping(value = "ExcelToPdfDownloads", method = RequestMethod.GET)
	public void downloadAllClassmate(HttpServletResponse response) throws IOException, DocumentException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("信息表");
		List<Address> classmateList = addressService.findAll();
		String fileName = "address" + ".xls";// 设置要导出的文件的名字
		// 新增数据行，并且设置单元格数据

		int rowNum = 1;
		String[] headers = { "AddressId", "AddressName" };
		// headers表示excel表中第一行的表头

		HSSFRow row = sheet.createRow(0);
		// 在excel表中添加表头

		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 在表中存放查询到的数据放入对应的列
		for (Address address : classmateList) {
			HSSFRow row1 = sheet.createRow(rowNum);
			row1.createCell(0).setCellValue(address.getAddressId());
			row1.createCell(1).setCellValue(address.getAddressName());
			rowNum++;
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		response.flushBuffer();
		/*
		 * OutputStream out = response.getOutputStream(); workbook.write(out);
		 * out.close(); System.out.println("导出excel成功~");
		 */
		// OutputStream out = response.getOutputStream();
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
	     /* String title = "标题";
	      document.add(new Paragraph(new Chunk(title, fontZH).setLocalDestination(title)));
	      document.add(new Paragraph("\n"));
	      // 创建table,注意这里的2是两列的意思,下面通过table.addCell添加的时候必须添加整行内容的所有列
	      PdfPTable table = new PdfPTable(2);
	      table.setWidthPercentage(100.0F);
	      table.setHeaderRows(1);
	      table.getDefaultCell().setHorizontalAlignment(1);
	      table.addCell(new Paragraph("序号", fontZH));
	      table.addCell(new Paragraph("结果", fontZH));
	      table.addCell(new Paragraph("1", fontZH));
	      table.addCell(new Paragraph("出来了", fontZH));
	      table.addCell(new Paragraph("2", fontZH));
	      table.addCell(new Paragraph("出来了", fontZH));
	      table.addCell(new Paragraph("3", fontZH));
	      table.addCell(new Paragraph("出来了", fontZH));
	      document.add(table);
	      document.add(new Paragraph("\n"));*/
	      
	      
	      // 第七步，关闭document
	      document.close();
	      System.out.println("导出pdf成功~");

  }

	

}
