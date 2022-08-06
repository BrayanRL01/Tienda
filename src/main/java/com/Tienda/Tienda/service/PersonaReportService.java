package com.Tienda.Tienda.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class PersonaReportService {

    private String reportPath;

    public String generateReport() {
        try {
            File file = ResourceUtils.getFile("classpath:ReportePersonas.jasper");
            reportPath = file.getParent();
// Carga el reporte
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
// Añade paramáteros (pero para este caso no hay)
            Map<String, Object> parameters = new HashMap<>();
//Instanciar la conexión
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tienda", "root", "brl092022");
// Llena el reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
// Exporta a PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\ReportePersonas.pdf");
            return reportPath + "\\ReportePersonas.pdf";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
