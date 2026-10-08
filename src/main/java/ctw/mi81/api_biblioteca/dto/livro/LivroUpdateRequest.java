package ctw.mi81.api_biblioteca.dto.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroUpdateRequest(

        @NotBlank(message = "O título do livro é obrigatório.")
        String titulo,

        @NotBlank(message = "O autor do livro é obrigatório.")
        String autor,

        @NotBlank(message = "O ISBN do livro é obrigatório.")
        String isbn,

        @NotNull(message = "O ano de publicação do livro é obrigatório.")
        Integer anoPublicacao
) {
}
