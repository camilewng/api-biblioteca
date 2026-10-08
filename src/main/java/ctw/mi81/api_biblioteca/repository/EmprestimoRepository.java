package ctw.mi81.api_biblioteca.repository;

import ctw.mi81.api_biblioteca.enums.StatusEmprestimo;
import ctw.mi81.api_biblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmprestimoRepository {

    private final List<Emprestimo> emprestimos = new ArrayList<>();
    private final AtomicLong sequencialId = new AtomicLong(1);

    public Emprestimo salvar(Emprestimo emprestimo){
        if (emprestimo.getId() == null){
            emprestimo.setId(sequencialId.getAndIncrement());
        } else {
            deletarPorId(emprestimo.getId());
        }
        emprestimos.add(emprestimo);
        return emprestimo;
    }

    public Optional<Emprestimo> buscarPorId(Long id){
        return emprestimos.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<Emprestimo> listarTodos(){
        return new ArrayList<>(emprestimos);
    }

    public boolean livroEmprestado(Long livroId){
        return emprestimos.stream()
                .anyMatch(e -> e.getLivro().getId().equals(livroId)
                          && e.getStatus() == StatusEmprestimo.ATIVO);
    }

    public boolean deletarPorId(Long id){
        return emprestimos.removeIf(e -> e.getId().equals(id));
    }
}
