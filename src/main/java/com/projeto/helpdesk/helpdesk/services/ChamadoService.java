package com.projeto.helpdesk.helpdesk.services;

import com.projeto.helpdesk.helpdesk.domain.Chamado;
import com.projeto.helpdesk.helpdesk.repositories.ChamadoRepository;
import com.projeto.helpdesk.helpdesk.services.exception.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjetcNotFoundException("Chamado não encontrado"));
    }
}
