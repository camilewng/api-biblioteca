package ctw.mi81.api_biblioteca.model;

import ctw.mi81.api_biblioteca.enums.StatusEmprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Emprestimo {
    private Long id;
    private Cliente cliente;
    private Livro livro;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataDevolucao;
    private StatusEmprestimo status;
    private BigDecimal multa;

    public Emprestimo() {
    }

    public Emprestimo(Long id, Cliente cliente, Livro livro, LocalDate dataInicio, LocalDate dataFim, LocalDate dataDevolucao, StatusEmprestimo status, BigDecimal multa) {
        this.id = id;
        this.cliente = cliente;
        this.livro = livro;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
        this.multa = multa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }
}

