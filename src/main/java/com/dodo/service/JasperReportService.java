package com.dodo.service;

import net.sf.jasperreports.engine.*;

import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

@RequestScoped
public class JasperReportService {

    private final DataSource dataSource;

    public JasperReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void generatedPdfReport(String jasperReportPath, String outputFileName, Map<String, Object> map) throws Exception {
        System.out.println("Connection " + dataSource.getConnection());
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportPath);
        Connection connection = null;
        try{
            connection = dataSource.getConnection();
            System.out.println("Connection " + connection);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
