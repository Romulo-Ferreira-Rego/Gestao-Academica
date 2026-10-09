package br.undb.semear.dominio;

import br.undb.semear.dominio.enums.StatusMatricula;
import br.undb.semear.dominio.enums.TipoDocumento;
import br.undb.semear.dominio.enums.TipoMatricula;
import br.undb.semear.excecao.DocumentoPendenteException;
import br.undb.semear.excecao.MatriculaInvalidaException;
import br.undb.semear.excecao.TurmaCheiaException;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Matricula {

    private static final Set<TipoDocumento> DOCUMENTOS_OBRIGATORIOS = EnumSet.of(
            TipoDocumento.RG,
            TipoDocumento.CPF,
            TipoDocumento.CERTIDAO_NASCIMENTO,
            TipoDocumento.CARTEIRA_VACINACAO,
            TipoDocumento.COMPROVANTE_RESIDENCIA,
            TipoDocumento.FOTO_3X4,
            TipoDocumento.AUTORIZACAO_BUSCA
    );

    private Aluno aluno;
    private Turma turma;
    private int anoLetivo;
    private TipoMatricula tipo;
    private StatusMatricula status;
    private boolean primeiraParcelaPaga;
    private boolean quitada;
    private boolean vindoDeInstituicaoParticular;
    private final Set<TipoDocumento> documentosEntregues = EnumSet.noneOf(TipoDocumento.class);

    public Matricula(Aluno aluno, Turma turma, int anoLetivo, TipoMatricula tipo) {
        this.aluno = Objects.requireNonNull(aluno, "aluno e obrigatorio");
        this.turma = Objects.requireNonNull(turma, "turma e obrigatoria");
        if (anoLetivo <= 0) {
            throw new IllegalArgumentException("anoLetivo invalido");
        }
        this.anoLetivo = anoLetivo;
        this.tipo = Objects.requireNonNull(tipo, "tipo e obrigatorio");
        this.status = StatusMatricula.PRE_MATRICULA;
        if (!turma.temVaga()) {
            throw new TurmaCheiaException();
        }
        aluno.adicionarMatricula(this);
        turma.adicionarMatricula(this);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public TipoMatricula getTipo() {
        return tipo;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public boolean isPrimeiraParcelaPaga() {
        return primeiraParcelaPaga;
    }

    public boolean isQuitada() {
        return quitada;
    }

    public Set<TipoDocumento> getDocumentosEntregues() {
        return Collections.unmodifiableSet(documentosEntregues);
    }

    public void marcarVindoDeInstituicaoParticular() {
        this.vindoDeInstituicaoParticular = true;
    }

    public void entregarDocumento(TipoDocumento documento) {
        documentosEntregues.add(Objects.requireNonNull(documento, "documento e obrigatorio"));
    }

    public void registrarPagamentoArras() {
        this.primeiraParcelaPaga = true;
    }

    public void quitar() {
        this.quitada = true;
    }

    public boolean documentosCompletos() {
        if (!documentosEntregues.containsAll(DOCUMENTOS_OBRIGATORIOS)) {
            return false;
        }
        return !vindoDeInstituicaoParticular || documentosEntregues.contains(TipoDocumento.DECLARACAO_ADIMPLENCIA);
    }

    public void aprovar() {
        if (status != StatusMatricula.PRE_MATRICULA) {
            throw new MatriculaInvalidaException("somente pre-matricula pode ser aprovada pela secretaria");
        }
        if (!aluno.estaAdimplente()) {
            throw new MatriculaInvalidaException("somente aluno adimplente pode ter matricula aceita ou renovada");
        }
        this.status = StatusMatricula.APROVADA;
    }

    public void rejeitar() {
        if (status != StatusMatricula.PRE_MATRICULA) {
            throw new MatriculaInvalidaException("somente pre-matricula pode ser rejeitada pela secretaria");
        }
        this.status = StatusMatricula.REJEITADA;
    }

    public void efetivar() {
        if (status != StatusMatricula.APROVADA) {
            throw new MatriculaInvalidaException("somente matricula aprovada pode ser efetivada");
        }
        if (!aluno.estaAdimplente()) {
            throw new MatriculaInvalidaException("somente aluno adimplente pode ter matricula aceita ou renovada");
        }
        if (!documentosCompletos()) {
            throw new DocumentoPendenteException();
        }
        if (!primeiraParcelaPaga) {
            throw new MatriculaInvalidaException("matricula so e efetivada com a primeira parcela paga");
        }
        this.status = StatusMatricula.ATIVA;
    }

    public void cancelar() {
        if (status != StatusMatricula.ATIVA && status != StatusMatricula.APROVADA) {
            throw new MatriculaInvalidaException("status nao permite cancelamento");
        }
        if (!quitada) {
            throw new MatriculaInvalidaException("cancelamento ou transferencia exige quitacao");
        }
        this.status = StatusMatricula.CANCELADA;
    }
}
