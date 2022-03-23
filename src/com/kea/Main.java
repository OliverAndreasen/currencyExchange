package com.kea;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Rateinfo> rateinfos=new ArrayList<Rateinfo>();

        //Instantiating the URL class
        URL url = new URL("https://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=da");
        //Retrieving the contents of the specified page
        Scanner sc = new Scanner(url.openStream());
        //Instantiating the StringBuffer class to hold the result
//        StringBuffer sb = new StringBuffer();
        int i=0;
        ArrayList<String> codes = new ArrayList<>();
        ArrayList<String> descs = new ArrayList<>();
        ArrayList<String> rates = new ArrayList<>();
        int count = 0;

        while(sc.hasNext())
        {
            String next=sc.next();
//            sb.append(next);
            i++;

            if (i>12 && i<181) {
                if(next.contains("code"))
                {
                    //System.out.print(count + " ");
                    count++;

                    String code = next.replaceAll("code=","").replaceAll("\"","");
                    //System.out.print(next.replaceAll("code=","").replaceAll("\"",""));
                    codes.add(code);
                }
                else if(next.contains("desc"))
                {
                    next=next.replaceAll("desc=","").replaceAll("\"","").replaceAll("�","æ");
                    descs.add(next);
                    if(next.contains("Euro")) {
                        //System.out.print(" " + next);
                    }else {
                        next=next+" "+ sc.next();
                        next=next.replaceAll("\"","");
                        //System.out.print(" " + next);
                    }
                }
                else if (next.contains("rate"))
                {
                    next=next.replaceAll("rate=","").replaceAll("\"","");
                    rates.add(next);
                    //System.out.print(" "+next);

                }
                if(next.contains("/>"))
                {
                    //System.out.println();

                }
            }
        }

        for (int j = 0; j < codes.size(); j++) {
            Rateinfo rateinfo = new Rateinfo(codes.get(j), descs.get(j), rates.get(j));
            //System.out.println(rateinfo.toString());
            rateinfos.add(rateinfo);
        }

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while(isRunning){
            System.out.println("Currency Exchange");
            System.out.println("Exchange from DKK press 1");
            int choice = scanner.nextInt();
            if(choice == 1) {
                System.out.println("how many dkk do you want to exchange");
                int currencyAmount = scanner.nextInt();
                System.out.println();
                System.out.println("choose which currency to exchange to");
                System.out.println();
                count = 0;
                for (Rateinfo rateinfo : rateinfos){
                    System.out.print("Press "+ count + " for - ");
                    System.out.print(rateinfo.getCode());
                    System.out.print(" - ");
                    System.out.println(rateinfo.getDesc());
                    System.out.println("---------------------------------------------------------");
                    count ++;
                }
                int rateInfoIndex = scanner.nextInt();

                Exchange exchange = new Exchange();
                System.out.println(exchange.exchangeDKKtoString(currencyAmount, rateinfos.get(rateInfoIndex)));

            }
        }
    }
}
