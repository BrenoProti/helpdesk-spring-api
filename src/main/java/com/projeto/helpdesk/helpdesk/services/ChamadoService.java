package com.projeto.helpdesk.helpdesk.services;

import com.projeto.helpdesk.helpdesk.domain.Chamado;
import com.projeto.helpdesk.helpdesk.domain.Cliente;
import com.projeto.helpdesk.helpdesk.domain.Tecnico;
import com.projeto.helpdesk.helpdesk.domain.dtos.ChamadoDTO;
import com.projeto.helpdesk.helpdesk.domain.enums.Prioridade;
import com.projeto.helpdesk.helpdesk.domain.enums.Status;
import com.projeto.helpdesk.helpdesk.repositories.ChamadoRepository;
import com.projeto.helpdesk.helpdesk.services.exception.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjetcNotFoundException("Chamado não encontrado"));
    }

    public List<ChamadoDTO> findAll() {
        return repository.findAll().stream().map(ChamadoDTO::new).collect(Collectors.toList());
    }

    public Chamado create(ChamadoDTO chamadoDto) {
        chamadoDto.setId(null);
        return repository.save(newChamado(chamadoDto));
    }

    private Chamado newChamado(ChamadoDTO chamadoDTO) {
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());
        Chamado chamado = new Chamado();
        if (chamadoDTO.getId() != null) {
            chamado.setId(chamadoDTO.getId());
        }
        chamado.setCliente(cliente);
        chamado.setTecnico(tecnico);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        return chamado;
    }
}
