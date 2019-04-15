package com.bchetty.reporting.jasper.business;

import com.bchetty.reporting.jasper.data.HelloWorldBean;

import java.util.ArrayList;


public class BarcodeBeanMaker {

    public ArrayList<HelloWorldBean> getDataBeanList() {
        ArrayList<HelloWorldBean> dataBeanList = new ArrayList<>();
        dataBeanList.add(produce("Babji, Chetty1", "Germany", 5));
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
