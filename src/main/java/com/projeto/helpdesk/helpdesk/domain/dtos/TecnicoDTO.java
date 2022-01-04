package com.projeto.helpdesk.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.helpdesk.helpdesk.domain.Tecnico;
import com.projeto.helpdesk.helpdesk.domain.enums.Perfil;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull
    protected String nome;
    @CPF
    @NotNull
    protected String cpf;
    @NotNull
    protected String email;
    @NotNull
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO(Tecnico tecnico) {
        super();
        addPerfil(Perfil.CLIENTE);
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();;
        this.cpf = tecnico.getCpf();;
        this.email = tecnico.getEmail();;
        this.senha = tecnico.getSenha();;
        this.perfis = tecnico.getPerfis().stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.dataCriacao = tecnico.getDataCriacao();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(it -> Perfil.toEnum(it)).collect(Collectors.toSet());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public TecnicoDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }


}
