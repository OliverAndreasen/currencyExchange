package com.kea;

public class Exchange {
    public double exchangeDKK(int dkkAmount, Rateinfo rateInfo) {
        double value = 100/ rateInfo.getRate();
        return value*dkkAmount;
    }


    public String exchangeDKKtoString(int dkkAmount, Rateinfo rateInfo){
        String result = "";
        double exchangedCurrency = exchangeDKK(dkkAmount,rateInfo);
        result += dkkAmount + " DKK svarer til " + exchangedCurrency + " " + rateInfo.getCode();
        return result;
    }
}
