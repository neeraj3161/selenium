package com.company;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class creatingPDf {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("Hello.pdf"));

        document.open();
//        document.add(new Paragraph("nn"));
        document.addAuthor("Neeraj");
        document.addTitle("First pdf");
//        document.setPageSize(PageSize.LETTER);

        //specify column width

        float[] columnWidth = {1.5f,2f,5f,2f};

        PdfPTable table = new PdfPTable(3);

        PdfPCell cell = new PdfPCell(new Phrase("One"));
        PdfPCell cell2 = new PdfPCell(new Phrase("One"));

        PdfPCell cell3 = new PdfPCell(new Phrase("One"));



        cell.setHorizontalAlignment(Element.ALIGN_LEFT);



        table.addCell(cell);
        table.addCell(cell2);

        table.addCell(cell3);
        table.addCell(cell3);
        table.addCell(cell3);


        //to avoid error
        //https://kb.itextpdf.com/home/it5kb/faq/why-is-content-missing-in-my-table

        Phrase phrase = new Phrase();
        phrase.add("Click ");
        Font link = FontFactory.getFont("Arial", 12, Font.ITALIC,BaseColor.BLUE);


        Chunk chunk = new Chunk("This link",link);
        chunk.setAnchor("https://www.google.com");
        phrase.add(chunk);
        cell = new PdfPCell(phrase);

        table.addCell(cell);
//        table.setWidthPercentage(100f);
//        table.setSpacingBefore(10f);

//        table.setSpacingAfter(10f);
//        float[] columnWidths = {2f, 1f, 1f};

//        table.setWidths(columnWidths);
        //http://tutorials.jenkov.com/java-itext/table.html

//        cell.setColspan(2);


        table.completeRow();

//// The complete cell is a link:
//        PdfPCell cell = new PdfPCell(new Phrase("Help us win a European Business Award!"));
//        cell.setCellEvent(new LinkInCell("http://itextpdf.com/blog/help-us-win-european-business-award"));
//        table.addCell(cell);
//        document.add(table);

        document.add(table);
        document.close();


    }
}
