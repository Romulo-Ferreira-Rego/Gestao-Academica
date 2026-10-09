package br.undb.semear.dominio;

import br.undb.semear.dominio.enums.Parentesco;

import java.util.Objects;

public class VinculoResponsavel {

    private Aluno aluno;
    private Responsavel responsavel;
    private Parentesco parentesco;
    private boolean responsavelFinanceiro;

    public VinculoResponsavel(Aluno aluno, Responsavel responsavel, Parentesco parentesco, boolean responsavelFinanceiro) {
        this.aluno = Objects.requireNonNull(aluno, "aluno e obrigatorio");
        this.responsavel = Objects.requireNonNull(responsavel, "responsavel e obrigatorio");
        this.parentesco = Objects.requireNonNull(parentesco, "parentesco e obrigatorio");
        this.responsavelFinanceiro = responsavelFinanceiro;
        aluno.adicionarVinculo(this);
        responsavel.adicionarVinculo(this);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public boolean isResponsavelFinanceiro() {
        return responsavelFinanceiro;
    }
}
