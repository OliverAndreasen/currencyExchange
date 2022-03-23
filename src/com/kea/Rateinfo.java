package com.kea;

public class Rateinfo {
    private String code;
    private String desc;
    private double rate;

    /**
     * @param Code Kode der anvendes for denne valuta
     * @param Desc Beskrivelse til valuta
     * @param Rate Antal danske kr for 100 af den valuta vi omregner til
     */
    public Rateinfo(String Code, String Desc, String Rate) {
        code=Code;
        desc=Desc;
        if(Rate.equals("-")) {
            Rate = "0,0";
        }
        Rate = Rate.replace(",", ".");
        rate=Double.parseDouble(Rate);
    }

    @Override
    public String toString() {
        return "Rateinfo{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public double getRate() {
        return rate;
    }
}
