package com.Tienda.Tienda.controller;

import com.Tienda.Tienda.entity.Pais;
import com.Tienda.Tienda.entity.Persona;
import com.Tienda.Tienda.service.IPaisService;
import com.Tienda.Tienda.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonaController {

    @Autowired
    private IPersonaService personaService;
    @Autowired
    private IPaisService paisService;

    @GetMapping("/personas")
    public String Index(Model model) {
        List<Persona> listaPersona = personaService.getAllPersona();
        model.addAttribute("titulo", "Tabla Personas");
        model.addAttribute("personas", listaPersona);
        return "personas";
    }

    @GetMapping("/personaN")
    public String CrearPersona(Model model) {
        List<Pais> listaPaises = paisService.listCountry();
        model.addAttribute("persona", new Persona());
        model.addAttribute("paises", listaPaises);
        return "crear";
    }

    @PostMapping("/saveP")
    public String GuardarPersona(@ModelAttribute Persona persona) {
        personaService.savePersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/editPersona/{id}")
    public String EditarPersona(@PathVariable("id") Long idPersona, Model model) {
        Persona persona = personaService.getPersonaById(idPersona);
        List<Pais> listaPais = paisService.listCountry();
        model.addAttribute("persona", persona);
        model.addAttribute("paises", listaPais);
        return "crear";
    }

    @GetMapping("/delete/{id}")
    public String EliminarPersona(Persona persona) {
        personaService.delete(persona.getid());
        return "redirect:/personas";
    }
}
