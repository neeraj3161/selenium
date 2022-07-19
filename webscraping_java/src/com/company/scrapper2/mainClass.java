package com.company.scrapper2;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class mainClass {
    public static void main(String[] args) throws InterruptedException, DocumentException, FileNotFoundException {
        searchProduct sp = new searchProduct();
        Scanner sc = new Scanner(System.in);
        String input = "";
        do {
            System.out.println("Welcome to amazon scraping tool using selenium.\nPlease select the options from below");
            System.out.println("1.Search product\n2.Search product with policy\n3.Quit");
            System.out.print("--->  ");
            input = sc.nextLine();

            switch (input){
                case "1":
                    sp.searchProduct();
                    break;
                case "2":
                    System.out.println("two");
                    break;
                case "3":
                    System.out.println("Quit");
                    break;
                default:
                    System.out.println("\nWrong option selected please make a correct choice\n");
            }
        }while(!input.equals("3"));
    }
}
