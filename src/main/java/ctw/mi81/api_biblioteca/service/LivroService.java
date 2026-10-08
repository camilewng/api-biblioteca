package ctw.mi81.api_biblioteca.service;

import ctw.mi81.api_biblioteca.dto.livro.LivroCreateRequest;
import ctw.mi81.api_biblioteca.dto.livro.LivroResponse;
import ctw.mi81.api_biblioteca.dto.livro.LivroUpdateRequest;
import ctw.mi81.api_biblioteca.model.Livro;

import ctw.mi81.api_biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponse cadastrar(LivroCreateRequest request) {
        if (livroRepository.buscarPorIsbn(request.isbn()).isPresent()) {
            throw new RuntimeException("Já existe um livro cadastrado com o ISBN informado.");
        }
        Livro livro = new Livro();
        livro.setTitulo(request.titulo());
        livro.setAutor(request.autor());
        livro.setIsbn(request.isbn());
        livro.setAnoPublicacao(request.anoPublicacao());

        Livro livroCadastrado = livroRepository.salvar(livro);

        return new LivroResponse(
                livroCadastrado.getId(),
                livroCadastrado.getTitulo(),
                livroCadastrado.getAutor(),
                livroCadastrado.getIsbn(),
                livroCadastrado.getAnoPublicacao(),
                livroCadastrado.getDisponivel()
        );
    }

    public LivroResponse buscarPorId(Long id) {
        Livro livro = livroRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));

        return new LivroResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getDisponivel()
        );
    }

    public List<LivroResponse> listarTodos() {
        return livroRepository.listarTodos().stream()
                .map(l -> new LivroResponse(
                        l.getId(),
                        l.getTitulo(),
                        l.getAutor(),
                        l.getIsbn(),
                        l.getAnoPublicacao(),
                        l.getDisponivel()
                )).toList();
    }

    public LivroResponse atualizar(Long id, LivroUpdateRequest request) {
        Livro livroExistente = livroRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));

        livroExistente.setTitulo(request.titulo());
        livroExistente.setAutor(request.autor());
        livroExistente.setAnoPublicacao(request.anoPublicacao());

        Livro livroAtualizado = livroRepository.salvar(livroExistente);

        return new LivroResponse(
                livroAtualizado.getId(),
                livroAtualizado.getTitulo(),
                livroAtualizado.getAutor(),
                livroAtualizado.getIsbn(),
                livroAtualizado.getAnoPublicacao(),
                livroAtualizado.getDisponivel()
        );
    }

    public void deletar(Long id) {
        if (!livroRepository.deletarPorId(id)) {
            throw new RuntimeException("ID não encontardo. Não foi possível excluir o livro.");
        }
    }
}
