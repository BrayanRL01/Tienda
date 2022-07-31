package com.Tienda.Tienda.API;

import com.Tienda.Tienda.Model.ReportePersonaDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;

public interface ReportePersonaServiceAPI {

    ReportePersonaDTO ObtenerReportePersona(Map<String, Object> params) throws JRException, IOException, SQLException;
}
