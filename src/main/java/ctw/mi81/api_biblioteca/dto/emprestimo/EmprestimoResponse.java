package ctw.mi81.api_biblioteca.dto.emprestimo;

import ctw.mi81.api_biblioteca.enums.StatusEmprestimo;
import ctw.mi81.api_biblioteca.model.Cliente;
import ctw.mi81.api_biblioteca.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmprestimoResponse(
        Long id,
        Long clienteId,
        String clienteNome,
        Long livroId,
        String livroTitulo,
        LocalDate dataInicio,
        LocalDate dataFim,
        LocalDate dataDevolucao,
        StatusEmprestimo status,
        BigDecimal multa
) {
}
