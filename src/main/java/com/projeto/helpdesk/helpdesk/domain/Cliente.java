package com.projeto.helpdesk.helpdesk.domain;

import com.projeto.helpdesk.helpdesk.domain.enums.Perfil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Cliente extends Pessoa{

    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(List<Chamado> chamados) {
        super();
        this.chamados = chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha, Set<Perfil> perfis, List<Chamado> chamados) {
        super(id, nome, cpf, email, senha);
    }
}
