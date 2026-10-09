package br.undb.semear.dominio;

import br.undb.semear.dominio.enums.Sexo;
import br.undb.semear.dominio.enums.Serie;
import br.undb.semear.dominio.enums.StatusMatricula;
import br.undb.semear.dominio.enums.TamanhoFarda;
import br.undb.semear.dominio.enums.TipoDocumento;
import br.undb.semear.dominio.enums.TipoMatricula;
import br.undb.semear.dominio.enums.Turno;
import br.undb.semear.excecao.DocumentoPendenteException;
import br.undb.semear.excecao.MatriculaInvalidaException;
import br.undb.semear.excecao.TurmaCheiaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegrasMatriculaTest {

    private int proximoId = 1;

    @Test
    void rn04_naoMatriculaAcimaDe30() {
        Turma turma = novaTurma(2026);
        for (int i = 1; i <= Turma.CAPACIDADE; i++) {
            turma.matricular(novoAluno("Aluno Sintetico " + i));
        }

        assertFalse(turma.temVaga());
        Aluno extra = novoAluno("Aluno Extra");
        assertThrows(TurmaCheiaException.class, () -> turma.matricular(extra));
    }

    @Test
    void rn05_secretariaAprovaOuRejeitaPreMatricula() {
        Turma turma = novaTurma(2026);
        Matricula preMatricula = turma.matricular(novoAluno("Ana Silva"));
        assertEquals(StatusMatricula.PRE_MATRICULA, preMatricula.getStatus());

        preMatricula.aprovar();
        assertEquals(StatusMatricula.APROVADA, preMatricula.getStatus());

        Matricula outra = turma.matricular(novoAluno("Bruno Souza"));
        outra.rejeitar();
        assertEquals(StatusMatricula.REJEITADA, outra.getStatus());
        assertTrue(turma.temVaga());
    }

    @Test
    void rn12_matriculaSoEfetivadaComPrimeiraParcelaEDocumentos() {
        Turma turma = novaTurma(2026);
        Matricula matricula = turma.matricular(novoAluno("Carla Dias"));
        matricula.aprovar();

        assertThrows(DocumentoPendenteException.class, matricula::efetivar);

        entregarDocumentosObrigatorios(matricula);
        assertThrows(MatriculaInvalidaException.class, matricula::efetivar);

        matricula.registrarPagamentoArras();
        matricula.efetivar();
        assertEquals(StatusMatricula.ATIVA, matricula.getStatus());
    }

    @Test
    void rn13_soAceitaMatriculaDeAlunoAdimplente() {
        Aluno aluno = novoAluno("Diego Lima");
        Turma turma2025 = novaTurma(2025);
        Matricula anterior = turma2025.matricular(aluno);
        anterior.aprovar();
        entregarDocumentosObrigatorios(anterior);
        anterior.registrarPagamentoArras();
        anterior.efetivar();
        assertFalse(aluno.estaAdimplente());
        assertThrows(MatriculaInvalidaException.class, anterior::cancelar);

        Turma turma2026 = novaTurma(2026);
        Matricula rematricula = new Matricula(aluno, turma2026, 2026, TipoMatricula.REMATRICULA);
        assertThrows(MatriculaInvalidaException.class, rematricula::aprovar);

        anterior.quitar();
        assertTrue(aluno.estaAdimplente());
        rematricula.aprovar();
        assertEquals(StatusMatricula.APROVADA, rematricula.getStatus());

        anterior.cancelar();
        assertEquals(StatusMatricula.CANCELADA, anterior.getStatus());
    }

    private Turma novaTurma(int anoLetivo) {
        return new Turma("Turma A", Serie.ANO_1, Turno.MATUTINO, anoLetivo);
    }

    private Aluno novoAluno(String nome) {
        Endereco endereco = new Endereco("Rua das Flores", "100", "Centro", "Sao Luis", "MA", "65000-000");
        FichaSaude fichaSaude = new FichaSaude(false, null, null, null);
        return new Aluno(proximoId++, nome, "000.000.000-00", LocalDate.of(2018, 3, 10),
                endereco, "98900000000", Sexo.FEMININO, TamanhoFarda.M, TamanhoFarda.P, true, fichaSaude);
    }

    private static void entregarDocumentosObrigatorios(Matricula matricula) {
        matricula.entregarDocumento(TipoDocumento.RG);
        matricula.entregarDocumento(TipoDocumento.CPF);
        matricula.entregarDocumento(TipoDocumento.CERTIDAO_NASCIMENTO);
        matricula.entregarDocumento(TipoDocumento.CARTEIRA_VACINACAO);
        matricula.entregarDocumento(TipoDocumento.COMPROVANTE_RESIDENCIA);
        matricula.entregarDocumento(TipoDocumento.FOTO_3X4);
        matricula.entregarDocumento(TipoDocumento.AUTORIZACAO_BUSCA);
    }
}
