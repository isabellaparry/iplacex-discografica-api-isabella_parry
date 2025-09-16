package org.iplacex.evaluacionu2.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.evaluacionu2.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DiscoController {
    @Autowired
    private IDiscoRepository discoRepo;
    private IArtistaRepository artistaRepo;

    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco){
            Disco temp = discoRepo.insert(disco);
            return new ResponseEntity<>(temp, null, HttpStatus.CREATED);
    }


    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id){
        Optional<Disco> temp = discoRepo.findById(id);

        if (!temp.isPresent()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } 

        return new ResponseEntity<>(temp.get(), null, HttpStatus.OK);
    }


    @GetMapping(
        value = "/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest(){
        List<Disco> discos = discoRepo.findAll();

        return new ResponseEntity<>(discos, null, HttpStatus.OK);
    }


    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String id_artista){
        List<Disco> discos = discoRepo.findDiscosByIdArtista(id_artista);

        if (discos == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } 

        return new ResponseEntity<>(discos, null, HttpStatus.OK);
    }

}
