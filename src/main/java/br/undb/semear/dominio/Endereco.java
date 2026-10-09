package br.undb.semear.dominio;

public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(String logradouro, String numero, String bairro, String cidade, String uf, String cep) {
        this.logradouro = exigirTexto(logradouro, "logradouro");
        this.numero = exigirTexto(numero, "numero");
        this.bairro = exigirTexto(bairro, "bairro");
        this.cidade = exigirTexto(cidade, "cidade");
        this.uf = exigirTexto(uf, "uf");
        this.cep = exigirTexto(cep, "cep");
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    private static String exigirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " e obrigatorio");
        }
        return valor.trim();
    }
}
