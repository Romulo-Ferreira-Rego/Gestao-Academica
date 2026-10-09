package br.undb.semear.dominio;

import br.undb.semear.dominio.enums.TipoDocumento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Funcionario extends Pessoa {

    private LocalDate dataAdmissao;
    private String rg;
    private String filiacao;
    private final List<TipoDocumento> documentosEntregues = new ArrayList<>();

    protected Funcionario(Integer id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String contato,
                          LocalDate dataAdmissao, String rg, String filiacao) {
        super(id, nome, cpf, dataNascimento, endereco, contato);
        this.dataAdmissao = Objects.requireNonNull(dataAdmissao, "dataAdmissao e obrigatorio");
        this.rg = exigirTexto(rg, "rg");
        this.filiacao = exigirTexto(filiacao, "filiacao");
    }

    public abstract String getFuncao();

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public String getRg() {
        return rg;
    }

    public String getFiliacao() {
        return filiacao;
    }

    public List<TipoDocumento> getDocumentosEntregues() {
        return Collections.unmodifiableList(documentosEntregues);
    }

    public void adicionarDocumento(TipoDocumento documento) {
        documentosEntregues.add(Objects.requireNonNull(documento, "documento e obrigatorio"));
    }
}
