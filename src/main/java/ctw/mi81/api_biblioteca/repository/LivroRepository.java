package ctw.mi81.api_biblioteca.repository;

import ctw.mi81.api_biblioteca.model.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LivroRepository {

    private final List<Livro> livros = new ArrayList<>();
    private final AtomicLong sequencialId = new AtomicLong(1);

    public Livro salvar(Livro livro){
        if (livro.getId() == null){
            livro.setId(sequencialId.getAndIncrement());
        } else {
            deletarPorId(livro.getId());
        }
        livros.add(livro);
        return livro;
    }

    public Optional<Livro> buscarPorId(Long id){
        return livros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    public List<Livro> listarTodos(){
        return new ArrayList<>(livros);
    }

    public Optional<Livro> buscarPorIsbn(String isbn){
        return livros.stream()
                .filter(l -> l.getIsbn().equalsIgnoreCase(isbn))
                .findFirst();
    }

    public boolean deletarPorId(Long id){
        return livros.removeIf(l -> l.getId().equals(id));
    }
}
