package com.Tienda.Tienda.service;

import com.Tienda.Tienda.entity.Persona;
import com.Tienda.Tienda.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getAllPersona() {
        return (List<Persona>) personaRepository.findAll();
    }

    @Override
    public void savePersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void delete(long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona getPersonaById(long id) {
        return personaRepository.findById(id).orElse(null);
    }
    
    @Override
    public Persona findByNombre (String nombre) {
        return personaRepository.findByNombre(nombre);
    }

}
