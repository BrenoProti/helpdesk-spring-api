package com.projeto.helpdesk.helpdesk.services;

import com.projeto.helpdesk.helpdesk.domain.Pessoa;
import com.projeto.helpdesk.helpdesk.domain.Tecnico;
import com.projeto.helpdesk.helpdesk.domain.dtos.TecnicoDTO;
import com.projeto.helpdesk.helpdesk.repositories.PessoaRepository;
import com.projeto.helpdesk.helpdesk.repositories.TecnicoRepository;
import com.projeto.helpdesk.helpdesk.services.exception.DataIntegrityViolationException;
import com.projeto.helpdesk.helpdesk.services.exception.ObjetcNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjetcNotFoundException("Técnico não encontrado!"));
    }

    public List<TecnicoDTO> findAll() {
        return repository.findAll().stream().map(TecnicoDTO::new).collect(Collectors.toList());
    }

    public Tecnico create(TecnicoDTO tecnicoDto) {
        tecnicoDto.setId(null);
        validateCpfAndEmail(tecnicoDto);
        Tecnico novoTecnico = new Tecnico(tecnicoDto);
        return repository.save(novoTecnico);
    }

    private void validateCpfAndEmail(TecnicoDTO tecnicoDto) {
        Optional<Pessoa> byCpf = pessoaRepository.findByCpf(tecnicoDto.getCpf());
        if (byCpf.isPresent() && byCpf.get().getId() != tecnicoDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        Optional<Pessoa> byEmail = pessoaRepository.findByEmail(tecnicoDto.getEmail());
        if (byEmail.isPresent() && byEmail.get().getId() != tecnicoDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

    public Tecnico update(Integer id, TecnicoDTO tecnicoDto) {
        tecnicoDto.setId(id);
        findById(id);
        validateCpfAndEmail(tecnicoDto);
        Tecnico tecnico = new Tecnico(tecnicoDto);
        return repository.save(tecnico);
    }

    public Tecnico delete(Integer id) {
        Tecnico tecnico = findById(id);
        if (!CollectionUtils.isEmpty(tecnico.getChamados())) {
            throw new DataIntegrityViolationException("Técnico possui chamado, e não pode ser deletado!");
        }
        repository.deleteById(id);
        return tecnico;
    }
}
