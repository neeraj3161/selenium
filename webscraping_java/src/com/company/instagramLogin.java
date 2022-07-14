package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class instagramLogin {
    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\your_user\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("w3c", true);
        chromeOptions.addArguments("user-data-dir=C:/Users/neera/AppData/Local/Google/Chrome/User Data/");
        chromeOptions.addArguments("--start-maximized");
        //check how to use proxy
        chromeOptions.addArguments("--proxy-server=80.48.119.28:8080");
        driver = new ChromeDriver(chromeOptions);


        Thread.sleep(1000);
        driver.get("https://www.instagram.com/user_id/followers");

//        Set<Cookie> cookies = driver.manage().getCookies();
//        System.out.println(cookies);
        Thread.sleep(5000);

//        WebElement l=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[1]/div/div/div/div[1]/div[1]/section/main/div/div[3]/article/div/div/div[2]/div[2]/a/div/div[2]"));
        // Javascript executor
//        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);

        //bring user followers
//        List<WebElement> userInfo = driver.findElements(By.className("_ac2a"));
//        int followersCount= Integer.parseInt(userInfo.get(1).getText());
        //formula followers count/11






        int j=0;
        Thread.sleep(1000);
        while(j<600) {
            ((JavascriptExecutor) driver).executeScript("document.querySelector(\"._aano\").scrollBy(0,1000);");
            Thread.sleep(2000);
            Thread.sleep(2000);
            System.out.println("Loading followers...");
            List<WebElement> followerList1  = driver.findElements(By.className("_aaei"));
            for (int i = 0; i < followerList1.size(); i++) {
                System.out.println("No."+(i+1));
                System.out.println(followerList1.get(i).findElement(By.className("_aade")).getText());
                System.out.println(followerList1.get(i).findElement(By.className("_aaeq")).getText());
                System.out.println();
                j=followerList1.size();
                Thread.sleep(1000);

            }


        }


        Thread.sleep(2000);
        System.out.println("Loading followers...");
        List<WebElement> followerList1  = driver.findElements(By.className("_aaei"));
        for (int i = 0; i < followerList1.size(); i++) {
            System.out.println("No."+(i+1));
            System.out.println(followerList1.get(i).findElement(By.className("_aade")).getText());
            System.out.println(followerList1.get(i).findElement(By.className("_aaeq")).getText());
            System.out.println();
        }


        driver.close();




//        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/div/label/input")).sendKeys("");
//        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/div/label/input")).sendKeys("");
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[3]/button")).click();
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter otp to continue: ");
//        String otp = sc.nextLine();
//        Thread.sleep(500);
//        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/div[1]/div[2]/form/div[1]/div/label/input")).sendKeys(otp);
//        driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/section/main/div/div/div[1]/div[2]/form/div[2]/button")).click();
//        Thread.sleep(5000);
//        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/div/section/div/button")).click();
//        Thread.sleep(500);



    }
}
