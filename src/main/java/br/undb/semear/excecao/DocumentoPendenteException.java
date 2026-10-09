package br.undb.semear.excecao;

public class DocumentoPendenteException extends RuntimeException {

    public DocumentoPendenteException() {
        super("Documentos obrigatorios pendentes para efetivar a matricula");
    }
}
