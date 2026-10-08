package ctw.mi81.api_biblioteca.dto.livro;

public record LivroResponse(
        Long id,
        String titulo,
        String autor,
        String isbn,
        Integer anoPublicacao,
        Boolean disponivel

) {
}
