package com.projeto.helpdesk.helpdesk.resources;

import com.projeto.helpdesk.helpdesk.domain.Cliente;
import com.projeto.helpdesk.helpdesk.domain.dtos.ChamadoDTO;
import com.projeto.helpdesk.helpdesk.domain.dtos.ClienteDTO;
import com.projeto.helpdesk.helpdesk.services.ChamadoService;
import com.projeto.helpdesk.helpdesk.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ChamadoDTO(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<ChamadoDTO> clientes = service.findAll();
        return ResponseEntity.ok().body(clientes);
    }
//
//    @PostMapping
//    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
//        Cliente cliente = service.create(clienteDTO);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO clienteDto) {
//        Cliente cliente = service.update(id, clienteDto);
//        return ResponseEntity.ok().body(new ClienteDTO(cliente));
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
//        Cliente cliente = service.delete(id);
//        return ResponseEntity.ok().body(new ClienteDTO(cliente));
//    }
}
