package ctw.mi81.api_biblioteca.dto.emprestimo;

import ctw.mi81.api_biblioteca.model.Cliente;

public record EmprestimoCreateRequest(
        Cliente clienteId,
        Long livroId
        ) {
}
