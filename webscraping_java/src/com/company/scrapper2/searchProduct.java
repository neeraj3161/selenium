package com.company.scrapper2;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class searchProduct {
    //will search product with price, product link, rating
    String productName,filter,priceFilter;
    WebDriver driver;
    ArrayList<String> ProductName = new ArrayList<>();
    ArrayList<String>Price=new ArrayList<>();

    ArrayList<String>link=new ArrayList<>();
    ArrayList<String>rating = new ArrayList<>();


    public void searchProduct() throws InterruptedException, DocumentException, FileNotFoundException {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\neera\\Downloads\\chromedriver_win32\\chromedriver.exe");
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Note: Press 'q' anytime to quit the program\n");
            System.out.print("Enter the product name you'd like to scrape: ");
            productName = sc.nextLine();
            productName=productName.replace(" ","+");
            System.out.print("Would you like to add a price filter (y/n): ");
            filter=sc.next();
            switch (filter){
                case "y":
                    System.out.print("Enter the price range(>0): ");

                    try{priceFilter = sc.next();
                        if(Integer.parseInt(priceFilter)<=0){
                            System.out.println("Invalid price please try again");
                            break;
                        }
                    }catch (Exception e){
                        System.out.println("Enter a number!!");
                        break;
                    }
                    searchProducts(productName);
                    break;
                case "n":
                    searchProducts(productName);
                    break;
                default:
                    System.out.println("Incorrect selection!!");
                    break;
            }

        }while(!productName.equals("q"));
    }

    private void searchProducts(String productName) throws InterruptedException, DocumentException, FileNotFoundException {

        int total=0;
        driver = new ChromeDriver();
        int page =    getPageInfo("https://www.amazon.in/s?k="+productName);
        System.out.println(page);


        for(int i=1; i<=page;i++){
            String url = "https://www.amazon.in/s?k="+productName+"&page="+i+"&s=price-asc-rank";
            driver.get(url);
            List<WebElement> productList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
            for(int j=0;j< productList.size();j++){
                total= total+productList.size();
                System.out.println(productList.get(j).findElement(By.tagName("h2")).getText());
                ProductName.add(productList.get(j).findElement(By.tagName("h2")).getText());
                try{
                    System.out.println(productList.get(j).findElement(By.className("a-price-whole")).getText());
                    Price.add(productList.get(j).findElement(By.className("a-price-whole")).getText().replace(",",""));
                }catch(Exception e){
                    System.out.println("No price for this product!!");
                    Price.add("0");
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

        }
        driver.close();
        System.out.println("No of products scraped: "+ProductName.size());
        System.out.println("Creating pdf file...");
        createPdf(productName);
        System.out.println("Thank you for using the scrapper tool!!");
        System.exit(0);
    }


    private int getPageInfo(String search){
        System.out.println("Getting page info...");
        //https://www.amazon.in/s?k=pencil
        driver.get("https://www.amazon.in/s?k="+search);
        driver.manage().window().maximize();
        ArrayList<String> pageList = new ArrayList<>();


        List<WebElement> pageLimit = driver.findElements(By.className("s-pagination-disabled"));
        for(WebElement i:pageLimit) {
            pageList.add(i.getText());
        }
        System.out.println("No of pages: "+Integer.parseInt(pageList.get(1)));
        return Integer.parseInt(pageList.get(1));
    }

    private void createPdf(String productName) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\neera\\OneDrive\\Desktop\\ScappedResult\\"+productName+".pdf");
        PdfWriter.getInstance(document,fileOutputStream);
        document.open();
        document.addTitle(productName);
        document.add(new Paragraph("Note: If price field is 0 means 'NO PRICE PRESENT FOR THE PRODUCT'\n\n"));
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Product name"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Price"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Product link"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Rating"));
        table.addCell(cell);
        if(filter.equals("n")){
            for (int i = 0; i < ProductName.size(); i++) {
                cell=new PdfPCell(new Phrase(ProductName.get(i)));
                table.addCell(cell);
                cell=new PdfPCell(new Phrase(Price.get(i)));
                table.addCell(cell);
                Chunk chunk = new Chunk("Product link", FontFactory.getFont(FontFactory.COURIER,10f,BaseColor.BLUE));
                chunk.setAnchor(link.get(i));
                cell=new PdfPCell(new Phrase(chunk));
                table.addCell(cell);
                cell=new PdfPCell(new Phrase(rating.get(i)));
                table.addCell(cell);
                table.completeRow();
            }
        }else if(filter.equals("y")){
            for (int i = 0; i < ProductName.size(); i++) {
                if(Integer.parseInt(Price.get(i))<=Integer.parseInt(priceFilter)){
                    cell=new PdfPCell(new Phrase(ProductName.get(i)));
                    table.addCell(cell);
                    cell=new PdfPCell(new Phrase(Price.get(i)));
                    table.addCell(cell);
                    Chunk chunk = new Chunk("Product link", FontFactory.getFont(FontFactory.COURIER,10f,BaseColor.BLUE));
                    chunk.setAnchor(link.get(i));
                    cell=new PdfPCell(new Phrase(chunk));
                    table.addCell(cell);
                    cell=new PdfPCell(new Phrase(rating.get(i)));
                    table.addCell(cell);
                    table.completeRow();
                }
            }
        }

        document.add(table);
        document.close();

        if(table.isComplete()){
            System.out.println("C:\\\\Users\\\\neera\\\\OneDrive\\\\Desktop\\\\ScappedResult\\\\\"+productName+\".pdf");
        }
    }


}
