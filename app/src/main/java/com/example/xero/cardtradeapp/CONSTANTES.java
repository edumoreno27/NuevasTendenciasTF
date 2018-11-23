package com.example.xero.cardtradeapp;

public class CONSTANTES {
    private String URLBASE;

    public CONSTANTES() {
        this.URLBASE= "http://cardtradeapirest.us-west-2.elasticbeanstalk.com/api/";
    }

    public String getURLBASE() {
        return URLBASE;
    }

    public void setURLBASE(String URLBASE) {
        this.URLBASE = URLBASE;
    }
}
