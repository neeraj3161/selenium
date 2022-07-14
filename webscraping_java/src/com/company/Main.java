package com.company;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static WebDriver driver;
    static int total=0;



    public static void main(String[] args) throws InterruptedException, DocumentException, FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the product you would like to search: ");
        String productName = sc.nextLine();
        productName=productName.replace(" ","+");

        System.setProperty("webdriver.chrome.driver","C:\\Users\\neera\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("w3c", true);
        driver = new ChromeDriver(chromeOptions);

        int numberOfPages = getPageInfo(productName);
        start(productName,numberOfPages);


    }

    private static void start(String search,int page) throws InterruptedException, DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("newReport.pdf"));

        document.open();
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell = new PdfPCell(new Phrase("Product name"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Product price"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Product link"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Product rating"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        ArrayList<String>ProductName = new ArrayList<>();
        ArrayList<String>Price=new ArrayList<>();

        ArrayList<String>link=new ArrayList<>();
        ArrayList<String>rating = new ArrayList<>();




        for(int i=1; i<=page;i++){
            driver.get("https://www.amazon.in/s?k="+search+"&page="+i);
            //Xpath =//tagname[@Attribute=’value’]
            List<WebElement> productList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
            for(int j=0;j< productList.size();j++){
                total= total+productList.size();
                System.out.println(productList.get(j).findElement(By.tagName("h2")).getText());
                ProductName.add(productList.get(j).findElement(By.tagName("h2")).getText());
                try{
                    System.out.println(productList.get(j).findElement(By.className("a-price-whole")).getText());
                    Price.add(productList.get(j).findElement(By.className("a-price-whole")).getText());
                }catch(Exception e){
                    System.out.println("No price for this product!!");
//                    System.out.println("No review for this product!!");
                    Price.add("No price!!");
                }

                try{
                    rating.add(productList.get(j).findElement(By.className("a-icon-alt")).getAttribute("innerHTML"));
                    System.out.println(productList.get(j).findElement(By.className("a-icon-alt")).getAttribute("innerHTML"));

                }catch(Exception e){
                    rating.add("No rating!!");
                }
                System.out.println(productList.get(j).findElement(By.tagName("a")).getAttribute("href"));
                link.add(productList.get(j).findElement(By.tagName("a")).getAttribute("href"));

            }
            System.out.println("Page no: "+i);
            Thread.sleep(1000);

        }

        createPdf(ProductName,link,Price,table,rating,cell);


        document.add(table);

        document.close();
        System.out.println(total);

        driver.close();
    }

    private static int getPageInfo(String search){
        //https://www.amazon.in/s?k=pencil


        driver.get("https://www.amazon.in/s?k="+search);
        driver.manage().window().maximize();
        ArrayList<String> pageList = new ArrayList<>();


        List<WebElement> pageLimit = driver.findElements(By.className("s-pagination-disabled"));
            for(WebElement i:pageLimit) {
                pageList.add(i.getText());
            }
            return Integer.parseInt(pageList.get(1));
    }

    private static void createPdf(ArrayList<String> name, ArrayList<String> link, ArrayList<String> price, PdfPTable table, ArrayList<String> rating, PdfPCell cell) throws FileNotFoundException, DocumentException {

            for(int j=0;j< name.size();j++){
                Phrase phrase = new Phrase(name.get(j));
                cell= new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);


                    phrase = new Phrase(price.get(j));
                    cell= new PdfPCell(phrase);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
//                    System.out.println(list.get(j).findElement(By.className("a-price-whole")).getText());
//                    System.out.println(list.get(j).findElement(By.className("a-icon-alt")).getAttribute("innerHTML"));

//                System.out.println(list.get(j).findElement(By.tagName("a")).getAttribute("href"));
                phrase = new Phrase();
                Font links = FontFactory.getFont("Arial", 12, Font.ITALIC,BaseColor.BLUE);


                Chunk chunk = new Chunk(link.get(j),links);
                chunk.setAnchor(link.get(j));
                phrase.add(chunk);
                cell = new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);


                phrase = new Phrase(rating.get(j));
                cell= new PdfPCell(phrase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

            }



        table.completeRow();


    }
}
