package ctw.mi81.api_biblioteca.dto.cliente;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClienteCreateRequest(

        @NotBlank(message = "O nome do cliente é obrigatório.")
        String nome,

        @NotBlank(message = "O CPF do cliente é obrigatório.")
        String cpf,

        @NotBlank(message = "O e-mail do cliente é obrigatório.")
        String email
) {
}
