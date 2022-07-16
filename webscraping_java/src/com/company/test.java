package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class test {


    static int countPairsWithDiff(int arr[],
                                   int n, int k)
    {
        int count = 0;

        for (int i = 0; i < n; i++)
        {

            for (int j = i + 1; j < n; j++)
                if (arr[i] - arr[j] == k ||
                        arr[j] - arr[i] == k)
                    count++;
        }
        return count;
    }


    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        int[] storeEl = new int[2];
        for (int j = 0; j <2 ; j++) {
            storeEl[j]=s.nextInt();
        }



        int arr[]=new int[storeEl[0]];
        for(int i=0;i<storeEl[0];i++){
            arr[i]=s.nextInt();

        }

        if(storeEl[0]>=1 && storeEl[1]>=1 &&storeEl[0]<=100000 && storeEl[1]<=1000000000){
            int n = arr.length;
            int k = storeEl[1];
            System.out.println( countPairsWithDiff(arr, n, k));
        }
    }
}


