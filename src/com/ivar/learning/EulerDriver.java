package com.ivar.learning;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class EulerDriver {

    public static int[][] input= {
        {8,2,22,97,38,15,0,40,0,75,4,5,7,78,52,12,50,77,91,8},
        {49,49,99,40,17,81,18,57,60,87,17,40,98,43,69,48,4,56,62,0},
        {81,49,31,73,55,79,14,29,93,71,40,67,53,88,30,3,49,13,36,65},
        {52,70,95,23,4,60,11,42,69,24,68,56,1,32,56,71,37,2,36,91},
        {22,31,16,71,51,67,63,89,41,92,36,54,22,40,40,28,66,33,13,80},
        {24,47,32,60,99,3,45,2,44,75,33,53,78,36,84,20,35,17,12,50},
        {32,98,81,28,64,23,67,10,26,38,40,67,59,54,70,66,18,38,64,70},
        {67,26,20,68,2,62,12,20,95,63,94,39,63,8,40,91,66,49,94,21},
        {24,55,58,5,66,73,99,26,97,17,78,78,96,83,14,88,34,89,63,72},
        {21,36,23,9,75,0,76,44,20,45,35,14,0,61,33,97,34,31,33,95},
        {78,17,53,28,22,75,31,67,15,94,3,80,4,62,16,14,9,53,56,92},
        {16,39,5,42,96,35,31,47,55,58,88,24,0,17,54,24,36,29,85,57},
        {86,56,0,48,35,71,89,7,5,44,44,37,44,60,21,58,51,54,17,58},
        {19,80,81,68,5,94,47,69,28,73,92,13,86,52,17,77,4,89,55,40},
        {4,52,8,83,97,35,99,16,7,97,57,32,16,26,26,79,33,27,98,66},
        {88,36,68,87,57,62,20,72,3,46,33,67,46,55,12,32,63,93,53,69},
        {4,42,16,73,38,25,39,11,24,94,72,18,8,46,29,32,40,62,76,36},
        {20,69,36,41,72,30,23,88,34,62,99,69,82,67,59,85,74,4,36,16},
        {20,73,35,29,78,31,90,1,74,31,49,71,48,86,81,16,23,57,5,54},
        {1,70,54,71,83,51,54,69,16,92,33,48,61,43,52,1,89,19,67,48}
    };

    private static class Element{
        int x;
        int y;
        boolean horizontal;
        boolean vertical;
        boolean leftDiagonal;
        boolean rightDiagonal;

        public Element(int x, int y, boolean horizontal, boolean vertical, boolean leftDiagonal,
            boolean rightDiagonal) {
            this.x = x;
            this.y = y;
            this.horizontal = horizontal;
            this.vertical = vertical;
            this.leftDiagonal = leftDiagonal;
            this.rightDiagonal = rightDiagonal;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "," + horizontal + "," + vertical + "," + leftDiagonal + "," + rightDiagonal + ']';
        }
    }
    public static void main(String[] args) {
        //kaprekarNumbers(1, 99999);
        problem01Multipleof3or5(10);
        problem01Multipleof3or5(100);
        problem01Multipleof3or5(1000000);
        problem02EvenFibonacciNumbers(100);
        problem03LargestPrimeFactor(600851475143L);
        problem04LargestPalindromeProduct();
        problem05SmallestMultiple();
        //primePairSets();
        //largestProductInAGrid(input, 4);
    }

    public static void problem05SmallestMultiple() {
    }

    public static void problem04LargestPalindromeProduct() {
        int max = -1;
        for ( int i = 99; i >= 10 ; i--) {
            if(i%11!=0){
                continue;
            }
            for (int j = 99 ; j >= 10 ; j-- ) {
                int p = i * j;
                if (max < p && isPalindrome(String.valueOf(p))) {

                    max = p;

                }
            }
        }
        System.out.println(String.format("found palindorme:%s",max));

    }

    private static boolean isPalindrome(String value) {
        if(value==null || value.length()<2){
            return true;
        }
        StringBuilder sb = new StringBuilder(value);
        StringBuilder sbReverse = new StringBuilder(value).reverse();
        //StringBuilder sb = new StringBuilder(x*x-1);
        if (sb.toString().equals(sbReverse.toString())) {
            return true;
        }
        return false;
    }

    private static void primePairSets() {
        int i=3;
        int counter = 0;
        List<Integer> list = new ArrayList<>();
        while(true){
            if(isPrime(i) && !checkPairPrime(i,list)){
                list.add(i);
                counter ++;
                System.out.println(String.format("i:%s,counter:%s",i,counter));
            }
            if(counter==5){
                break;
            }
            i++;
            System.out.println(String.format("i:%s",i));
        }
        System.out.println(list.stream().reduce((i1,i2)->i1+i2));
    }

    private static boolean checkPairPrime(int i, List<Integer> list) {
        if(list==null || list.isEmpty()){
            return false;
        }
        return list.stream().filter(ele->{
            if(!isPrime(Long.parseLong(String.valueOf(ele).concat(String.valueOf(i))))  || !isPrime(Long.parseLong(String.valueOf(i).concat(String.valueOf(ele))))){
                return true;
            }
            return false;
        }).findAny().isPresent();

    }

    public static void problem03LargestPrimeFactor(long l) {
        BigInteger lValue = BigInteger.valueOf(l);
        BigInteger value = BigInteger.valueOf(Math.round(Math.sqrt(lValue.doubleValue())));
        int result = IntStream.rangeClosed(1,value.intValue()).boxed().sorted(
            Collections.reverseOrder()).filter(ele->lValue.divideAndRemainder(BigInteger.valueOf(ele.longValue()))[1].intValue()==0 && isPrime(ele)).findFirst().orElse(0);
        System.out.println(result);
    }

    private static boolean isPrime(int number) {

        // Even numbers
        if (number % 2 == 0) {
            return number == 2;
        }

        // Odd numbers
        // int limit = (int)(0.1 + Math.sqrt(number));
        for (int i = 3; i*i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPrime(long number) {

        // Even numbers
        if (number % 2 == 0) {
            return number == 2;
        }

        // Odd numbers
        // int limit = (int)(0.1 + Math.sqrt(number));
        for (int i = 3; i*i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void problem02EvenFibonacciNumbers(int max) {

        int sum = 0;
        int fib = 1;
        int prev = 1;
        int tmp = 0;
        System.out.println(Integer.toBinaryString(1));
        while (fib <= 4000000) {
            tmp = fib;
            fib += prev;
            prev = tmp;
            System.out.println(Integer.toBinaryString(fib));
            if ((fib & 1) == 0) {
                sum += fib;
            }
        }

        System.out.println(sum);
    }

    public static void problem01Multipleof3or5(int i) {
        int sum = IntStream.range(1,i).filter(ele->(ele%3==0|| ele%5==0)).sum();
        System.out.println(sum);
        int n3 = (i-1)/3;
        int n5 = (i-1)/5;
        int n15 = (i-1)/15;

        int sum3 = n3*(n3+1)*3/2;
        int sum5 = n5*(n5+1)*5/2;
        int sum15 = n15*(n15+1)*15/2;

        System.out.println(sum3+sum5-sum15);

    }


    public static List<String> fetchItemsToDisplay(List<List<String>> items, int sortParameter, int sortOrder, int itemsPerPage, int pageNumbet){

        List<String> allItems = new ArrayList<>();
        //allItems.stream().sorted((o1, o2) -> {o1[]})
        return null;
    }


    private static void largestProductInAGrid(int[][] input, int maxSize) {
        Set<Element> elements = new HashSet<>();
        for(int i=0;i<input.length;i++){
            boolean horizontal =false;
            boolean vertical=false;
            boolean leftDiagonal = false;
            boolean rightDiagonal = false;
            for(int j=0;j<input[i].length;j++){

                if(j<input.length-3){
                    // System.out.print("("+i+","+j+")");
                    horizontal = true;
                    rightDiagonal = true;
                }
                if(i<input.length-3){
                    //System.out.print("("+i+","+j+")");
                    vertical = true;
                    leftDiagonal = true;
                }
                elements.add(new Element(i,j,horizontal,vertical,leftDiagonal,rightDiagonal));
            }


        }
        System.out.println(elements.size());
        System.out.println(String.join(":",elements.toString()));
    }

    static void kaprekarNumbers(int p, int q) {
        if(p<0 || q<p || q>100000){
            System.out.println("INVALID RANGE");
            return;
        }
        List<String> answer = new ArrayList<>();
        IntStream.rangeClosed(p,q).mapToObj(ele->{
            System.out.println(ele);
            return new BigInteger(String.valueOf(ele));}).forEach(element->{
            int digits = element.toString().length();
            BigInteger pweValue = element.pow(2);
            String result = pweValue.toString();
            //List<String> answer = new ArrayList<>();
            if(result.length()>1){
                String rightResult = result.substring(result.length()-digits, result.length());
                String leftResult = result.substring(0,result.length()-digits);
                BigInteger finalResult = new BigInteger(rightResult).add(new BigInteger(leftResult));
                if(finalResult.equals(element)){
                    //System.out.print(element);
                    answer.add(element.toString());
                }
            }else{
                if(pweValue.equals(element)){
                    answer.add(element.toString());
                }
            }



        });
        if(answer==null || answer.size()==0){
            System.out.println("INVALID RANGE");
            return;
        }
        System.out.println(String.join(" ", answer).trim());
    }

}
