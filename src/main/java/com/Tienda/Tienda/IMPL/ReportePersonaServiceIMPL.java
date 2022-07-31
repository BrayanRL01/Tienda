package com.Tienda.Tienda.IMPL;

import com.Tienda.Tienda.API.ReportePersonaServiceAPI;
import com.Tienda.Tienda.Commons.JasperReportManager;
import com.Tienda.Tienda.Enums.TipoReporteEnum;
import com.Tienda.Tienda.Model.ReportePersonaDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportePersonaServiceIMPL implements ReportePersonaServiceAPI {

    @Autowired
    private JasperReportManager RM;

    @Autowired
    private DataSource DS;

    @Override
    public ReportePersonaDTO ObtenerReportePersona(Map<String, Object> params) throws JRException, IOException, SQLException {
        String FileName = "reporte_de_personas";
        ReportePersonaDTO RPD = new ReportePersonaDTO();
        String Extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx" : ".pdf";
        RPD.setFileName(FileName + Extension);
        ByteArrayOutputStream S = RM.export(FileName, params.get("tipo").toString(), params, DS.getConnection());

        byte[] BS = S.toByteArray();
        RPD.setStream(new ByteArrayInputStream(BS));
        RPD.setLength(BS.length);
        return RPD;
    }

}
