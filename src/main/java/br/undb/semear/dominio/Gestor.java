package br.undb.semear.dominio;

import java.time.LocalDate;

public class Gestor extends Funcionario {

    public Gestor(Integer id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String contato,
                  LocalDate dataAdmissao, String rg, String filiacao) {
        super(id, nome, cpf, dataNascimento, endereco, contato, dataAdmissao, rg, filiacao);
    }

    @Override
    public String getFuncao() {
        return "Gestor";
    }
}
