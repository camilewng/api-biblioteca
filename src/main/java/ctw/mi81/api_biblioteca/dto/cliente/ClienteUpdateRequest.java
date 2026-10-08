package ctw.mi81.api_biblioteca.dto.cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteUpdateRequest(

        @NotBlank(message = "O nome do cliente é obrigatório.")
        String nome,

        @NotBlank(message = "O CPF do cliente é obrigatório.")
        String cpf,

        @NotBlank(message = "O e-mail do cliente é obrigatório.")
        String email
) {
}
