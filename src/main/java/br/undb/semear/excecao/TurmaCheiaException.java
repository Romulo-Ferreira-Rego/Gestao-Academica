package br.undb.semear.excecao;

public class TurmaCheiaException extends RuntimeException {

    public TurmaCheiaException() {
        super("Turma atingiu a capacidade maxima de 30 alunos");
    }
}
