package ctw.mi81.api_biblioteca.dto.emprestimo;

import ctw.mi81.api_biblioteca.model.Cliente;

public record EmprestimoUpdateRequest(
        Cliente clienteId,
        Long livroId
) {
}
