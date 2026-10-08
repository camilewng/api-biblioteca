package ctw.mi81.api_biblioteca.dto.emprestimo;

import ctw.mi81.api_biblioteca.model.Cliente;
import jakarta.validation.constraints.NotNull;

public record EmprestimoCreateRequest(

        @NotNull(message = "O ID do cliente é obrigatório.")
        Long clienteId,

        @NotNull(message = "O ID do livro é obrigatório.")
        Long livroId
        ) {
}
