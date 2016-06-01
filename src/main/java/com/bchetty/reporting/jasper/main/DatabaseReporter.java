package com.bchetty.reporting.jasper.main;

import com.zaxxer.hikari.HikariDataSource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by achmad on 01/06/16.
 */
public class DatabaseReporter {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseReporter.class);

    public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:jhipster;DB_CLOSE_DELAY=-1");
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
        final Sql2o sql = new Sql2o(dataSource);
        try (final Connection connection = sql.open()) {
            final Integer count = connection.createQuery("SELECT COUNT(1) FROM EMPLOYEE").executeScalar(Integer.class);
            LOG.info("Currently there are {} employee(s).", count);
        }
        try (final java.sql.Connection connection = dataSource.getConnection()) {
            InputStream inputStream = new FileInputStream("reports/employee.jrxml");

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
            JasperExportManager.exportReportToPdfFile(jasperPrint, "reports/employee.pdf");
            JasperViewer viewer = new JasperViewer(jasperPrint);
            viewer.setVisible(true);
        }
    }
}
