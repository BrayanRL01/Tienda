package com.Tienda.Tienda.Commons;

import com.Tienda.Tienda.Enums.TipoReporteEnum;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class JasperReportManager {

    private static final String REPORT_FOLDER = "reports";
    private static final String JASPER = ".jasper";

    public ByteArrayOutputStream export(String FileName, String TipoReporte, Map<String, Object> params, Connection C)
            throws JRException, IOException {
        
        ByteArrayOutputStream Stream = new ByteArrayOutputStream();
        ClassPathResource Resource = new ClassPathResource(REPORT_FOLDER + File.separator + FileName + JASPER);

        InputStream IS = Resource.getInputStream();
        JasperPrint JP = JasperFillManager.fillReport(IS, params, C);
        
        if (TipoReporte.equalsIgnoreCase(TipoReporteEnum.EXCEL.toString())) {
            JRXlsxExporter EX = new JRXlsxExporter();
            EX.setExporterInput(new SimpleExporterInput(JP));
            EX.setExporterOutput(new SimpleOutputStreamExporterOutput(Stream));

            SimpleXlsxReportConfiguration Conf = new SimpleXlsxReportConfiguration();
            Conf.setDetectCellType(true);
            Conf.setCollapseRowSpan(true);
            EX.setConfiguration(Conf);
            EX.exportReport();
        } else {
            JasperExportManager.exportReportToPdfStream(JP, Stream);
        }
        return Stream;
    }
}
