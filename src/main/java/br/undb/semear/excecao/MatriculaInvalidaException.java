package br.undb.semear.excecao;

public class MatriculaInvalidaException extends RuntimeException {

    public MatriculaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
