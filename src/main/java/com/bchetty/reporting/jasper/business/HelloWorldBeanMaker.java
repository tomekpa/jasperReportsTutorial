package com.bchetty.reporting.jasper.business;

import com.bchetty.reporting.jasper.data.HelloWorldBean;

import java.util.ArrayList;


public class HelloWorldBeanMaker {

    public ArrayList<HelloWorldBean> getDataBeanList() {
        ArrayList<HelloWorldBean> dataBeanList = new ArrayList<>();

        dataBeanList.add(produce("Babji, Chetty1", "Germany", 5));
        dataBeanList.add(produce("Albert Einstein", "Germany", 4));
        dataBeanList.add(produce("Alfred Hitchcock", "UK", 8));
        dataBeanList.add(produce("Wernher Von Braun", "Poland (Previously Germany)", 9));
        dataBeanList.add(produce("Sigmund Freud", "", 3));
        dataBeanList.add(produce("Mahatma Gandhi", "", 8));
        dataBeanList.add(produce("Sachin Tendulkar", "India", 7));
        dataBeanList.add(produce("Michael Schumacher", "Germany", 2));

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
