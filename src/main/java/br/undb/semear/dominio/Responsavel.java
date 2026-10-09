package br.undb.semear.dominio;

import br.undb.semear.dominio.enums.EstadoCivil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Responsavel extends Pessoa {

    private EstadoCivil estadoCivil;
    private String profissao;
    private final List<VinculoResponsavel> vinculos = new ArrayList<>();

    public Responsavel(Integer id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String contato,
                       EstadoCivil estadoCivil, String profissao) {
        super(id, nome, cpf, dataNascimento, endereco, contato);
        this.estadoCivil = Objects.requireNonNull(estadoCivil, "estadoCivil e obrigatorio");
        this.profissao = exigirTexto(profissao, "profissao");
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public String getProfissao() {
        return profissao;
    }

    public List<VinculoResponsavel> getVinculos() {
        return Collections.unmodifiableList(vinculos);
    }

    void adicionarVinculo(VinculoResponsavel vinculo) {
        vinculos.add(vinculo);
    }
}
