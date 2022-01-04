package com.projeto.helpdesk.helpdesk.resources;

import com.projeto.helpdesk.helpdesk.domain.Tecnico;
import com.projeto.helpdesk.helpdesk.domain.dtos.TecnicoDTO;
import com.projeto.helpdesk.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new TecnicoDTO(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<TecnicoDTO> tecnicos = service.findAll();
        return ResponseEntity.ok().body(tecnicos);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO tecnicoDto) {
        Tecnico tecnico = service.create(tecnicoDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @RequestBody TecnicoDTO tecnicoDto) {
        Tecnico tecnico = service.update(id, tecnicoDto);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }
}
