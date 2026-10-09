package br.undb.semear.dominio;

import br.undb.semear.dominio.enums.Serie;
import br.undb.semear.dominio.enums.StatusMatricula;
import br.undb.semear.dominio.enums.TipoMatricula;
import br.undb.semear.dominio.enums.Turno;
import br.undb.semear.excecao.TurmaCheiaException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Turma {

    public static final int CAPACIDADE = 30;

    private String nome;
    private Serie serie;
    private Turno turno;
    private int anoLetivo;
    private final List<Matricula> matriculas = new ArrayList<>();
    private final List<Modulo> modulos = new ArrayList<>();

    public Turma(String nome, Serie serie, Turno turno, int anoLetivo) {
        this.nome = Pessoa.exigirTexto(nome, "nome");
        this.serie = Objects.requireNonNull(serie, "serie e obrigatoria");
        this.turno = Objects.requireNonNull(turno, "turno e obrigatorio");
        if (anoLetivo <= 0) {
            throw new IllegalArgumentException("anoLetivo invalido");
        }
        this.anoLetivo = anoLetivo;
    }

    public String getNome() {
        return nome;
    }

    public Serie getSerie() {
        return serie;
    }

    public Turno getTurno() {
        return turno;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public List<Matricula> getMatriculas() {
        return Collections.unmodifiableList(matriculas);
    }

    public List<Modulo> getModulos() {
        return Collections.unmodifiableList(modulos);
    }

    public void adicionarModulo(Modulo modulo) {
        Objects.requireNonNull(modulo, "modulo e obrigatorio");
        if (!modulos.contains(modulo)) {
            modulos.add(modulo);
            modulo.adicionarTurma(this);
        }
    }

    public Matricula matricular(Aluno aluno) {
        Objects.requireNonNull(aluno, "aluno e obrigatorio");
        if (!temVaga()) {
            throw new TurmaCheiaException();
        }
        return new Matricula(aluno, this, anoLetivo, TipoMatricula.NOVA);
    }

    public boolean temVaga() {
        return quantidadeOcupada() < CAPACIDADE;
    }

    int quantidadeOcupada() {
        int ocupadas = 0;
        for (Matricula matricula : matriculas) {
            if (ocupaVaga(matricula.getStatus())) {
                ocupadas++;
            }
        }
        return ocupadas;
    }

    void adicionarMatricula(Matricula matricula) {
        if (!temVaga()) {
            throw new TurmaCheiaException();
        }
        matriculas.add(matricula);
    }

    private static boolean ocupaVaga(StatusMatricula status) {
        return status == StatusMatricula.PRE_MATRICULA
                || status == StatusMatricula.APROVADA
                || status == StatusMatricula.ATIVA;
    }
}
