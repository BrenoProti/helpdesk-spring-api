package com.projeto.helpdesk.helpdesk.services;

import com.projeto.helpdesk.helpdesk.domain.Cliente;
import com.projeto.helpdesk.helpdesk.domain.Pessoa;
import com.projeto.helpdesk.helpdesk.domain.dtos.ClienteDTO;
import com.projeto.helpdesk.helpdesk.repositories.ClienteRepository;
import com.projeto.helpdesk.helpdesk.repositories.PessoaRepository;
import com.projeto.helpdesk.helpdesk.services.exception.DataIntegrityViolationException;
import com.projeto.helpdesk.helpdesk.services.exception.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjetcNotFoundException("Cliente não encontrado!"));
    }

    public List<ClienteDTO> findAll() {
        return repository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    public Cliente create(ClienteDTO clienteDto) {
        clienteDto.setId(null);
        validateCpfAndEmail(clienteDto);
        Cliente novoCliente = new Cliente(clienteDto);
        return repository.save(novoCliente);
    }

    private void validateCpfAndEmail(ClienteDTO clienteDto) {
        Optional<Pessoa> byCpf = pessoaRepository.findByCpf(clienteDto.getCpf());
        if (byCpf.isPresent() && byCpf.get().getId() != clienteDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        Optional<Pessoa> byEmail = pessoaRepository.findByEmail(clienteDto.getEmail());
        if (byEmail.isPresent() && byEmail.get().getId() != clienteDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

    public Cliente update(Integer id, ClienteDTO clienteDto) {
        clienteDto.setId(id);
        findById(id);
        validateCpfAndEmail(clienteDto);
        Cliente cliente = new Cliente(clienteDto);
        return repository.save(cliente);
    }

    public Cliente delete(Integer id) {
        Cliente cliente = findById(id);
        if (!CollectionUtils.isEmpty(cliente.getChamados())) {
            throw new DataIntegrityViolationException("Cliente possui chamado, e não pode ser deletado!");
        }
        repository.deleteById(id);
        return cliente;
    }
}
