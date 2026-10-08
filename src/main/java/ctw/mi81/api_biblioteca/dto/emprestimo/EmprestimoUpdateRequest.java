package ctw.mi81.api_biblioteca.dto.emprestimo;

import jakarta.validation.constraints.NotNull;

public record EmprestimoUpdateRequest(

        @NotNull(message = "O ID do cliente é obrigatório.")
        Long clienteId,

        @NotNull(message = "O ID do livro é obrigatório.")
        Long livroId
) {
}
