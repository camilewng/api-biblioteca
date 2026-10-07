package ctw.mi81.api_biblioteca.dto.cliente;

import java.time.LocalDate;

public record ClienteCreateRequest(
        String nome,
        String cpf,
        String email,
        LocalDate dataCadastro
) {
}
