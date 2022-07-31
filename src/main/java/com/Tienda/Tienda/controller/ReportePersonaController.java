package com.Tienda.Tienda.controller;

import com.Tienda.Tienda.API.ReportePersonaServiceAPI;
import com.Tienda.Tienda.Enums.TipoReporteEnum;
import com.Tienda.Tienda.Model.ReportePersonaDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportePersonaController {

    @Autowired
    private ReportePersonaServiceAPI RPSA;

    @GetMapping(path = "/personasJ/download")
    public ResponseEntity<Resource> download(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        ReportePersonaDTO RPD = RPSA.ObtenerReportePersona(params);
        InputStreamResource SR = new InputStreamResource(RPD.getStream());
        MediaType MT = null;
        if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
            MT = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            MT = MediaType.APPLICATION_PDF;
        }
        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + RPD.getFileName() + "\"").
                contentLength(RPD.getLength()).contentType(MT).body(SR);
    }
}
