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

        makePdfInJava();

//        Document document = new Document();
//        PdfWriter.getInstance(document,new FileOutputStream("Hello.pdf"));
//
//        document.open();
////        document.add(new Paragraph("nn"));
//        document.addAuthor("Neeraj");
//        document.addTitle("First pdf");
////        document.setPageSize(PageSize.LETTER);
//
//        //specify column width
//
//        float[] columnWidth = {1.5f,2f,5f,2f};
//
//        PdfPTable table = new PdfPTable(3);
//
//        PdfPCell cell = new PdfPCell(new Phrase("One"));
//        PdfPCell cell2 = new PdfPCell(new Phrase("One"));
//
//        PdfPCell cell3 = new PdfPCell(new Phrase("One"));
//
//
//
//        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//
//
//
//        table.addCell(cell);
//        table.addCell(cell2);
//
//        table.addCell(cell3);
//        table.addCell(cell3);
//        table.addCell(cell3);
//
//
//        //to avoid error
//        //https://kb.itextpdf.com/home/it5kb/faq/why-is-content-missing-in-my-table
//
//        Phrase phrase = new Phrase();
//        phrase.add("Click ");
//        Font link = FontFactory.getFont("Arial", 12, Font.ITALIC,BaseColor.BLUE);
//
//
//        Chunk chunk = new Chunk("This link",link);
//        chunk.setAnchor("https://www.google.com");
//        phrase.add(chunk);
//        cell = new PdfPCell(phrase);
//
//        table.addCell(cell);
////        table.setWidthPercentage(100f);
////        table.setSpacingBefore(10f);
//
////        table.setSpacingAfter(10f);
////        float[] columnWidths = {2f, 1f, 1f};
//
////        table.setWidths(columnWidths);
//        //http://tutorials.jenkov.com/java-itext/table.html
//
////        cell.setColspan(2);
//
//
//        table.completeRow();
//
////// The complete cell is a link:
////        PdfPCell cell = new PdfPCell(new Phrase("Help us win a European Business Award!"));
////        cell.setCellEvent(new LinkInCell("http://itextpdf.com/blog/help-us-win-european-business-award"));
////        table.addCell(cell);
////        document.add(table);
//
//        document.add(table);
//        document.close();


    }

    public static void makePdfInJava() throws FileNotFoundException, DocumentException {
        //make pdf in java using itext pdf

        //Path to the file
        /*Java FileOutputStream Class

        Java FileOutputStream is an output stream used for writing data to a file
        If you have to write primitive values into a file, use FileOutputStream class. You can write byte-oriented as well as character-oriented data through FileOutputStream class. But, for character-oriented data, it is preferred to use FileWriter
        than FileOutputStream.

         */
        FileOutputStream filePath = new FileOutputStream("C:\\Users\\neera\\OneDrive\\Desktop\\filename.pdf");

        //to write the pdf and save it to a path we use Document class in itext

        Document document = new Document();
        //instance of pdf writer
        PdfWriter.getInstance(document,filePath);
        //opening the pdf document

        document.open();
        //there are many uses of the document instance find it here: https://api.itextpdf.com/iText5/java/5.5.9/com/itextpdf/text/Document.html ---> basically it adds meta-data to the pdf document
        document.addTitle("Pdf Sample");

        //Now in order to create a pdf we use PdfPTable class and create an instance of it. This takes the input of no of columns as a parameter

        PdfPTable table = new PdfPTable(6);
        document.add(new Paragraph("This is a sample pdf generated\n\n"));


        //to add cells in the pdf

        PdfPCell cells;


        cells= new PdfPCell(new Phrase("1"));
        //adding this cell to the pdf
        table.addCell(cells);

        for (int i = 0; i <100 ; i++) {
//            cells = new PdfPCell(new Phrase(String.valueOf(i+1)));
            //adding links to the table
            Chunk chunk = new Chunk("Link",new Font(Font.FontFamily.COURIER,10f,Font.ITALIC,BaseColor.BLUE));
            chunk.setAnchor("https://www.google.com");
            cells = new PdfPCell(new Phrase(chunk));
            cells.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cells);
            System.out.println("LR");
        }

        //table.completeRow(); will complete the row and arrange the table
        table.completeRow();
        document.add(table);
        document.close();


    }
}
