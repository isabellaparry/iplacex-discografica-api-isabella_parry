package org.iplacex.evaluacionu2.discos;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Disco {
    @Id
    public String _id;
    
    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;
}
