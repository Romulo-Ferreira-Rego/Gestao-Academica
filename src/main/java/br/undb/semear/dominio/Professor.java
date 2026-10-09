package br.undb.semear.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Professor extends Funcionario {

    private final List<Modulo> modulos = new ArrayList<>();

    public Professor(Integer id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String contato,
                     LocalDate dataAdmissao, String rg, String filiacao) {
        super(id, nome, cpf, dataNascimento, endereco, contato, dataAdmissao, rg, filiacao);
    }

    @Override
    public String getFuncao() {
        return "Professor";
    }

    public List<Modulo> getModulos() {
        return Collections.unmodifiableList(modulos);
    }

    void adicionarModulo(Modulo modulo) {
        modulos.add(modulo);
    }
}
