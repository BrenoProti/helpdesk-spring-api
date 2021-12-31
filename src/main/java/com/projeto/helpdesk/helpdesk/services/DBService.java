package com.projeto.helpdesk.helpdesk.services;

import com.projeto.helpdesk.helpdesk.domain.Chamado;
import com.projeto.helpdesk.helpdesk.domain.Cliente;
import com.projeto.helpdesk.helpdesk.domain.Tecnico;
import com.projeto.helpdesk.helpdesk.domain.enums.Perfil;
import com.projeto.helpdesk.helpdesk.domain.enums.Prioridade;
import com.projeto.helpdesk.helpdesk.domain.enums.Status;
import com.projeto.helpdesk.helpdesk.repositories.ChamadoRepository;
import com.projeto.helpdesk.helpdesk.repositories.ClienteRepository;
import com.projeto.helpdesk.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;


    public void instanciarDB() {
        Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "293.956.790-50", "validr@email.com", "123");
        tecnicoRepository.saveAll(Arrays.asList(tec1));

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "256.221.610-53", "torvalds@email.com", "123");
        clienteRepository.saveAll(Arrays.asList(cli1));

        Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado teste", "Teste", tec1, cli1);
        chamadoRepository.saveAll(Arrays.asList(cha1));
    }
}
