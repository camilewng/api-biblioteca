package ctw.mi81.api_biblioteca.service;

import ctw.mi81.api_biblioteca.dto.emprestimo.EmprestimoCreateRequest;
import ctw.mi81.api_biblioteca.dto.emprestimo.EmprestimoResponse;
import ctw.mi81.api_biblioteca.enums.StatusEmprestimo;
import ctw.mi81.api_biblioteca.model.Cliente;
import ctw.mi81.api_biblioteca.model.Emprestimo;
import ctw.mi81.api_biblioteca.model.Livro;
import ctw.mi81.api_biblioteca.repository.ClienteRepository;
import ctw.mi81.api_biblioteca.repository.EmprestimoRepository;
import ctw.mi81.api_biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final ClienteRepository clienteRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             ClienteRepository clienteRepository,
                             LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.clienteRepository = clienteRepository;
        this.livroRepository = livroRepository;
    }

    public EmprestimoResponse realizarEmprestimo(EmprestimoCreateRequest request) {
        Cliente cliente = clienteRepository.buscarPorId(request.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Livro livro = livroRepository.buscarPorId(request.livroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado."));

        if (emprestimoRepository.livroEmprestado(livro.getId())) {
            throw new RuntimeException("O livro '" + livro.getTitulo() + "' já está emprestado.");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setLivro(livro);
        emprestimo.setDataInicio(LocalDate.now());
        emprestimo.setDataFim(LocalDate.now().plusDays(20));
        emprestimo.setStatus(StatusEmprestimo.ATIVO); emprestimo.setMulta(BigDecimal.ZERO);

        Emprestimo e = emprestimoRepository.salvar(emprestimo);
        return toResponse(e);
    }

    public EmprestimoResponse devolverLivro(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado."));

        if (emprestimo.getStatus() != StatusEmprestimo.ATIVO) {
            throw new RuntimeException("Este empréstimo já foi finalizado anteriormente.");
        }

        LocalDate dataDevolucao = LocalDate.now();
        emprestimo.setDataDevolucao(dataDevolucao);

        long diasAtraso = 0;
        LocalDate data = emprestimo.getDataFim();

        while (data.isBefore(dataDevolucao)) {
            data = data.plusDays(1);
            diasAtraso++;
        }

        if (diasAtraso > 0) {
            emprestimo.setMulta(new BigDecimal("2.50").multiply(BigDecimal.valueOf(diasAtraso)));
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO_COM_ATRASO);
        } else {
            emprestimo.setMulta(BigDecimal.ZERO);
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        }
        return toResponse(emprestimoRepository.salvar(emprestimo));
    }

    public EmprestimoResponse buscarPorId(Long id) {
        return emprestimoRepository.buscarPorId(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado."));
    }

    public List<EmprestimoResponse> listarTodos(){
        return emprestimoRepository.listarTodos().stream()
                .map(this::toResponse)
                .toList();
    }

    private EmprestimoResponse toResponse(Emprestimo e) {
        return new EmprestimoResponse(
                e.getId(),
                e.getCliente().getId(),
                e.getCliente().getNome(),
                e.getLivro().getId(),
                e.getLivro().getTitulo(),
                e.getDataInicio(),
                e.getDataFim(),
                e.getDataDevolucao(),
                e.getStatus(),
                e.getMulta()
        );
    }
}
