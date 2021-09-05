package com.example.demo.util;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfUtils {

    /**
     * Creates a table; widths are set with setWidths().
     *
     * @return a PdfPTable
     * @throws DocumentException
     */
    public static PdfPTable createTable1() throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(288 / 5.23f);
        table.setWidths(new int[] { 2, 1, 1 });

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Table 1"));
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        return table;
    }

    /**
     * Creates a table; widths are set with setWidths().
     *
     * @return a PdfPTable
     * @throws DocumentException
     */
    public static PdfPTable createTable2() throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(288);
        table.setLockedWidth(true);
        table.setWidths(new float[] { 2, 1, 1 });
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Table 2"));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        return table;
    }

    /**
     * Creates a table; widths are set in the constructor.
     *
     * @return a PdfPTable
     * @throws DocumentException
     */
    public static PdfPTable createTable3() throws DocumentException {
        PdfPTable table = new PdfPTable(new float[] { 2, 1, 1 });
        table.setWidthPercentage(55.067f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Table 3"));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        return table;
    }

    /**
     * Creates a table; widths are set with special setWidthPercentage() method.
     *
     * @return a PdfPTable
     * @throws DocumentException
     */
    public static PdfPTable createTable4() throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        Rectangle rect = new Rectangle(523, 770);
        table.setWidthPercentage(new float[] { 144, 72, 72 }, rect);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Table 4"));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        return table;
    }

    /**
     * Creates a table; widths are set with setTotalWidth().
     *
     * @return a PdfPTable
     * @throws DocumentException
     */
    public static PdfPTable createTable5() throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setTotalWidth(new float[] { 144, 72, 72 });
        table.setLockedWidth(true);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Table 5"));
        cell.setColspan(3);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
        cell.setRowspan(2);
        table.addCell(cell);
        table.addCell("row 1; cell 1");
        table.addCell("row 1; cell 2");
        table.addCell("row 2; cell 1");
        table.addCell("row 2; cell 2");
        return table;
    }

    public static PdfPTable createTable6() throws DocumentException{
        PdfPTable table = new PdfPTable(10);
        table.setTotalWidth(595);
        //table.setLockedWidth(true);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Table 6"));
        cell.setColspan(10);
        table.addCell(cell);

        for (int i = 1; i < 101; i++) {
            cell = new PdfPCell(new Phrase(String.valueOf(i)));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }
        return table;
    }
    public static PdfPTable createTable7() throws DocumentException, IOException{
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
        for (int i = 1; i < 101; i++) {
            cell = new PdfPCell(new Phrase(String.valueOf(i)));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }
        return table;
    }

    /*public void createPDF(String filename) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));

            document.addTitle("table of PDF");
            document.addAuthor("eff666");
            document.addSubject("This is the subject of the PDF file.");
            document.addKeywords("This is the keyword of the PDF file.");

            document.open();

            PdfPTable table = createTable1();
            document.add(table);

            table = createTable2();
            table.setSpacingBefore(5);
            table.setSpacingAfter(5);
            document.add(table);

            table = createTable3();
            document.add(table);

            table = createTable4();
            table.setSpacingBefore(5);
            table.setSpacingAfter(5);
            document.add(table);

            table = createTable5();
            document.add(table);

            table = createTable6();
            document.add(table);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static void main(String[] args) {

        PdfUtils pdf = new PdfUtils();

        String filename = "F:\\pdf\\pdf_table_001.pdf";
        pdf.createPDF(filename);
    }*/
    
}
