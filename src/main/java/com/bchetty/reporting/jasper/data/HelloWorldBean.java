package com.bchetty.reporting.jasper.data;

/*
 * @author Babji P, Chetty
 */
public class HelloWorldBean {
    private String name;
    private String country;
    private Integer counter;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}