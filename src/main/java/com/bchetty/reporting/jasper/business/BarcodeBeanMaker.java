package com.bchetty.reporting.jasper.business;

import com.bchetty.reporting.jasper.data.HelloWorldBean;

import java.util.ArrayList;


public class BarcodeBeanMaker {

    public ArrayList<HelloWorldBean> getDataBeanList() {
        ArrayList<HelloWorldBean> dataBeanList = new ArrayList<>();
        dataBeanList.add(produce("12134", "12340", 5));
//        dataBeanList.add(produce("121345", "12340n", 5)); //Caused by: net.sourceforge.barbecue.BarcodeException: n is not a valid character for Standard 2 of 5 encoding
        return dataBeanList;
    }

    private HelloWorldBean produce(String name, String country, Integer counter) {
        HelloWorldBean dataBean = new HelloWorldBean();
        dataBean.setName(name);
        dataBean.setCountry(country);
        dataBean.setCounter(counter);
        return dataBean;
    }
}
