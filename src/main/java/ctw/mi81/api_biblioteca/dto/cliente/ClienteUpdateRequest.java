package ctw.mi81.api_biblioteca.dto.cliente;

public record ClienteUpdateRequest(
        String nome,
        String cpf,
        String email
) {
}
