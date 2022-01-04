package com.projeto.helpdesk.helpdesk.services;

import com.projeto.helpdesk.helpdesk.domain.Chamado;
import com.projeto.helpdesk.helpdesk.domain.dtos.ChamadoDTO;
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

    public Chamado findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjetcNotFoundException("Chamado não encontrado"));
    }

    public List<ChamadoDTO> findAll() {
        return repository.findAll().stream().map(ChamadoDTO::new).collect(Collectors.toList());
    }
}
