package com.bchetty.reporting.jasper.main;

import com.bchetty.reporting.jasper.business.SimpleBeanMaker;
import com.bchetty.reporting.jasper.data.SimpleBean;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class SimpleReporter {

    public static void main(String[] args) throws Exception {

        //COMPILED JASPER REPORTS
        InputStream inputStream = new FileInputStream("reports/simple.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        //DATA COLLECTION
        SimpleBeanMaker simpleBeanMaker = new SimpleBeanMaker();
        ArrayList<SimpleBean> simpleBeanList = simpleBeanMaker.getMyDataBeanList();
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(simpleBeanList);

        //CREATE REPORT
        Map parameters = new HashMap();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);


        JasperExportManager.exportReportToPdfFile(jasperPrint, "reports/simple.pdf");
    }
}
