package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

 class RSA {
    public void Decrypt() throws FileNotFoundException {
        File encrypt = new File("Encrypt.txt");
        Scanner s2 = new Scanner(encrypt);
        String read1 = s2.nextLine();
        String[] z = read1.split(" ");
        int d = Integer.parseInt(z[0]);
        int n = Integer.parseInt(z[1]);
        while (s2.hasNextLine()){
            String read = s2.nextLine();
            String[] x = read.split(" ");
            for (int i = 0; i < x.length; i++) {
                int c = Integer.parseInt(x[i]);

                BigInteger N = BigInteger.valueOf(n);
                BigInteger C = BigDecimal.valueOf(c).toBigInteger();
                BigInteger m = (C.pow(d)).mod(N);
                String letters = "abcdefghijklmnopqrstuvwxyz";
                int h = Integer.parseInt(String.valueOf(m));
                System.out.print(letters.charAt(h));
            }
            System.out.print(" ");
        }

    }



    public void Encrypt() throws FileNotFoundException {
        File decrypt = new File("Decrypt.txt");
        Scanner s2 = new Scanner(decrypt);
        String read1 = s2.nextLine();
        String[] z = read1.split(" ");
        int e = Integer.parseInt(z[0]);
        int n = Integer.parseInt(z[1]);
        while (s2.hasNextLine()){
            String read = s2.nextLine();
            String[] x = read.split(" ");
            for (int i = 0; i < x.length; i++) {
                int m = Integer.parseInt(x[i]);

                BigInteger N = BigInteger.valueOf(n);
                BigInteger M = BigDecimal.valueOf(m).toBigInteger();
                BigInteger c = (M.pow(e)).mod(N);
                System.out.print(c + " ");
            }
            System.out.print(" ");
        }

    }



    public List<Integer> generate(int startingValue, int endingValue) {
        List<Integer> primesList = new ArrayList<Integer>();
        int minValue = Math.min(startingValue, endingValue);
        int maxValue = Math.max(startingValue, endingValue);
           for(int i = minValue; i <= maxValue; i++) {
               if(isPrime(i))   {    primesList.add(i);}
           }    return primesList;
    }
    public boolean isPrime(int value) {
        boolean isPrime = true;
        if(value < 2)  {   isPrime = false;  }  else  {
            for(int i = 2; i <= value/2; i++)   {
                if(value % i == 0) {
                    isPrime = false;
                    break;    }
            }
        }  return isPrime; }


    int gcd(int e, int z)
    {
        if(e==0)
            return z;
        else
            return gcd(z%e,e);
    }
    public  void createFile() throws IOException {
        Scanner s1 = new Scanner(System.in);
        s1.useDelimiter("\n");
        File encrypt = new File("Encrypt.txt");
        File decrypt = new File("Decrypt.txt");
        System.out.println("Enter word you want to create");

        String inputX = s1.next();
        String[] msgSplit = inputX.split(" ");
        System.out.println(Arrays.toString(msgSplit));


        FileWriter fileWriter = new FileWriter(decrypt);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        FileWriter fileWriter1 = new FileWriter(encrypt);
        BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);


        Random rand = new Random();

        String letters = "abcdefghijklmnopqrstuvwxyz";


        int p, q, n, k, d = 0, e, i;
        double c;
        BigInteger msgBack;
        p = sieveOfEratosthenes(191).get(rand.nextInt(sieveOfEratosthenes(191).size()));
        q = sieveOfEratosthenes(191).get(rand.nextInt(sieveOfEratosthenes(191).size()));
        n = p * q;
        k = (p - 1) * (q - 1);
        for (e = 2; e < k; e++) {
            if (gcd(e, k) == 1) {
                break;
            }
        }
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * k);
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        bufferedWriter.write(e + " " + n + "\n");
        bufferedWriter1.write(d + " " + n + "\n");


        for (int l = 0; l < msgSplit.length;l++){
            for (int j = 0; j < msgSplit[l].length(); j++) {
                int msg = letters.indexOf(msgSplit[l].charAt(j));
                c = (Math.pow(msg, e)) % n;
                System.out.println(c);
                bufferedWriter1.write((int) c + " ");
                /////////////////////////////////////////////////////
                BigInteger N = BigInteger.valueOf(n);
                BigInteger C = BigDecimal.valueOf(c).toBigInteger();
                msgBack = (C.pow(d)).mod(N);
                System.out.println(msgBack);
                bufferedWriter.write(msgBack + " ");
                ///////////////////////////////////////////////////////
            }
            bufferedWriter1.write("\n");
            bufferedWriter.write("\n");

        }
        bufferedWriter.close();
        bufferedWriter1.close();

    }

}

