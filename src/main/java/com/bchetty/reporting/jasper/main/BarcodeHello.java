package com.bchetty.reporting.jasper.main;

import com.bchetty.reporting.jasper.business.BarcodeBeanMaker;
import com.bchetty.reporting.jasper.business.HelloWorldBeanMaker;
import com.bchetty.reporting.jasper.data.HelloWorldBean;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//https://stackoverflow.com/questions/34759249/barcode-creation-in-jasper-report-n-is-not-a-valid-character-for-standard-2-of

public class BarcodeHello {

    //1 JR-XML File
    //2 JR-Xml-Loader - parse JR-XML file to Jasper Design
    //3 JR-Compiler - compiles loaded JR-XML file to Jasper Report
    //4 Jasper-Fill-Manager - fills compiled Jasper Report
    //5 Jasper-Print-Manager - prints filled report (pdf, html, xml, xls)
    public static void main(String[] args) throws Exception {

        // ADD 1 - InputStream - JR Template file
        InputStream inputStream = new FileInputStream("reports/BarcodeHello.jrxml");

        // ADD 2 - JasperDesign - JR Template file load
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

        //ADD 3 - JasperReport - JR JasperDesign compile
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        //ADD 4 - JasperPrint - JasperReport fill report
        BarcodeBeanMaker barcodeBeanMaker = new BarcodeBeanMaker();
        ArrayList<HelloWorldBean> simpleBeanList = barcodeBeanMaker.getDataBeanList();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(simpleBeanList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("MY_LP", "LP");
        parameters.put("MY_HEADER_NAME", "NAME");
        parameters.put("MY_HEADER_COUNTRY", "COUNTRY");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

        //ADD 5 - JasperExportManager - JasperPrint to PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, "reports/BarcodeHello.pdf");
//        JasperExportManager.exportReportToHtmlFile(jasperPrint, "reports/helloWorldTemplate.html");
    }
}