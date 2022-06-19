package com.Tienda.Tienda.service;

import com.Tienda.Tienda.entity.Persona;
import java.util.List;

public interface IPersonaService {

    public List<Persona> getAllPersona();

    public void savePersona(Persona persona);

    public void delete(long id);

    public Persona getPersonaById(long id);

}
