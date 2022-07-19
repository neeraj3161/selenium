package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class amazonNRI {

    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        //check chrome options??

        System.setProperty("webdriver.chrome.driver","C:\\Users\\neera\\Downloads\\chromedriver_win32\\chromedriver.exe");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the product name: ");
        String productName = sc.nextLine();
        productName=productName.replace(" ","+");
        driver = new ChromeDriver();
        makeReq(productName);

    }

    private static void makeReq(String url) throws InterruptedException {


        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/s?k="+url);

        ArrayList<String>productLinks = new ArrayList<>();

        int lastPageNumber = lastPageNumber();
        for (int k = 1; k <= lastPageNumber; k++) {
            Thread.sleep(1000);
            driver.get("https://www.amazon.in/s?k="+url+"&page="+k);
            List<WebElement>productAsins = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
            for (int i = 0; i < productAsins.size(); i++) {
                productLinks.add("https://www.amazon.in/dp/"+productAsins.get(i).getAttribute("data-asin"));
            }

        }

        System.out.println("Total products found: "+productLinks.size());

        //get product link and policy
        for (int j = 0; j < productLinks.size(); j++) {
            if(getPolicy(productLinks.get(j))==1){
                System.out.println(productLinks.get(j));
                System.out.println("\n");
            }
            if(j< productLinks.size()-1){
                System.out.print((j+1)+",");
            }else{
                System.out.print((j+1));
            }

        }
        System.out.println("Program ended!!");
        Thread.sleep(1000);

        driver.quit();
    }

    private static int getPolicy(String url) {
        try{
            driver.get(url);
            String whatTofind = driver.findElement(By.xpath("//div[@id='RETURNS_POLICY']")).findElement(By.tagName("a")).getText();
            if(whatTofind.contains("Non") || whatTofind.contains("Not")){
                System.out.println(whatTofind);
                return 1;
            }
        }catch (Exception e){
            System.out.println("Something went wrong for product: "+url);
        }
        return 0;
    }

    private static int lastPageNumber(){
       String lastInt =  driver.findElements(By.xpath("//span[@aria-disabled='true']")).get(1).getText();
        return Integer.parseInt(lastInt);
    }
}


