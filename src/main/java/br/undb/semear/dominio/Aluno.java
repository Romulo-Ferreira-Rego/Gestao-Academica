package br.undb.semear.dominio;

import br.undb.semear.dominio.enums.Sexo;
import br.undb.semear.dominio.enums.StatusMatricula;
import br.undb.semear.dominio.enums.TamanhoFarda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Aluno extends Pessoa {

    private FichaSaude fichaSaude;
    private Sexo sexo;
    private TamanhoFarda fardaEscolar;
    private TamanhoFarda fardaEdFisica;
    private boolean autorizaUsoImagem;
    private final List<VinculoResponsavel> vinculos = new ArrayList<>();
    private final List<Matricula> matriculas = new ArrayList<>();

    public Aluno(Integer id, String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String contato,
                 Sexo sexo, TamanhoFarda fardaEscolar, TamanhoFarda fardaEdFisica, boolean autorizaUsoImagem,
                 FichaSaude fichaSaude) {
        super(id, nome, cpf, dataNascimento, endereco, contato);
        this.sexo = Objects.requireNonNull(sexo, "sexo e obrigatorio");
        this.fardaEscolar = Objects.requireNonNull(fardaEscolar, "fardaEscolar e obrigatorio");
        this.fardaEdFisica = Objects.requireNonNull(fardaEdFisica, "fardaEdFisica e obrigatorio");
        this.autorizaUsoImagem = autorizaUsoImagem;
        this.fichaSaude = fichaSaude;
    }

    public FichaSaude getFichaSaude() {
        return fichaSaude;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public TamanhoFarda getFardaEscolar() {
        return fardaEscolar;
    }

    public TamanhoFarda getFardaEdFisica() {
        return fardaEdFisica;
    }

    public boolean isAutorizaUsoImagem() {
        return autorizaUsoImagem;
    }

    public List<VinculoResponsavel> getVinculos() {
        return Collections.unmodifiableList(vinculos);
    }

    public List<Matricula> getMatriculas() {
        return Collections.unmodifiableList(matriculas);
    }

    void adicionarVinculo(VinculoResponsavel vinculo) {
        vinculos.add(vinculo);
    }

    void adicionarMatricula(Matricula matricula) {
        matriculas.add(matricula);
    }

    public boolean estaAdimplente() {
        for (Matricula matricula : matriculas) {
            StatusMatricula status = matricula.getStatus();
            if (status == StatusMatricula.ATIVA || status == StatusMatricula.CANCELADA) {
                if (!matricula.isQuitada()) {
                    return false;
                }
            }
        }
        return true;
    }
}
