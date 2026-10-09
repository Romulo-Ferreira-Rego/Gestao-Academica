package br.undb.semear.dominio;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Pessoa {

    private Integer id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private String contato;

    protected Pessoa(Integer id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String contato) {
        this.id = Objects.requireNonNull(id, "id e obrigatorio");
        this.nome = exigirTexto(nome, "nome");
        this.cpf = exigirTexto(cpf, "cpf");
        this.dataNascimento = Objects.requireNonNull(dataNascimento, "dataNascimento e obrigatoria");
        this.endereco = Objects.requireNonNull(endereco, "endereco e obrigatorio");
        this.contato = exigirTexto(contato, "contato");
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getContato() {
        return contato;
    }

    static String exigirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " e obrigatorio");
        }
        return valor.trim();
    }
}
