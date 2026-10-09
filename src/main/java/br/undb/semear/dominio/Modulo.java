package br.undb.semear.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Modulo {

    private String nome;
    private int cargaHoraria;
    private Professor professor;
    private final List<Turma> turmas = new ArrayList<>();

    public Modulo(String nome, int cargaHoraria, Professor professor) {
        this.nome = Pessoa.exigirTexto(nome, "nome");
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("cargaHoraria deve ser positiva");
        }
        this.cargaHoraria = cargaHoraria;
        this.professor = Objects.requireNonNull(professor, "professor e obrigatorio");
        professor.adicionarModulo(this);
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Turma> getTurmas() {
        return Collections.unmodifiableList(turmas);
    }

    void adicionarTurma(Turma turma) {
        turmas.add(turma);
    }
}
