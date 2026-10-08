package ctw.mi81.api_biblioteca.service;

import ctw.mi81.api_biblioteca.dto.cliente.ClienteCreateRequest;
import ctw.mi81.api_biblioteca.dto.cliente.ClienteResponse;
import ctw.mi81.api_biblioteca.dto.cliente.ClienteUpdateRequest;
import ctw.mi81.api_biblioteca.model.Cliente;
import ctw.mi81.api_biblioteca.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse cadastrar (ClienteCreateRequest request){
        if(clienteRepository.buscarPorCpf(request.cpf()).isPresent()){
            throw new RuntimeException("Já existe um cliente cadastrado com o CPF informado: " + request.cpf());
        }

        Cliente cliente = new Cliente();
        cliente.setNome((request.nome()));
        cliente.setCpf(request.cpf());
        cliente.setEmail(request.email());

        Cliente clienteCadastrado = clienteRepository.salvar(cliente);

        return new ClienteResponse(
                clienteCadastrado.getId(),
                clienteCadastrado.getNome(),
                clienteCadastrado.getCpf(),
                clienteCadastrado.getEmail(),
                clienteCadastrado.getDataCadastro(),
                clienteCadastrado.getAtivo()
        );
    }

    public ClienteResponse buscarPorId(Long id){
        Cliente cliente = clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente com esse ID não encontrado."));

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getDataCadastro(),
                cliente.getAtivo()
        );
    }

    public List<ClienteResponse> listarTodos(){
        return clienteRepository.listarTodos().stream()
                .map(c -> new ClienteResponse(
                        c.getId(),
                        c.getNome(),
                        c.getCpf(),
                        c.getEmail(),c.getDataCadastro(),
                        c.getAtivo()
                ))
                .toList();
    }

    public ClienteResponse atualizar(Long id, ClienteUpdateRequest request){
        Cliente clienteExistente = clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente com esse ID não encontrado"));

        clienteExistente.setNome(request.nome());
        clienteExistente.setCpf(request.cpf());
        clienteExistente.setEmail(request.email());

        Cliente clienteAtualizado = clienteRepository.salvar(clienteExistente);

        return new ClienteResponse(
                clienteAtualizado.getId(),
                clienteAtualizado.getNome(),
                clienteAtualizado.getCpf(),
                clienteAtualizado.getEmail(),
                clienteAtualizado.getDataCadastro(),
                clienteAtualizado.getAtivo()
        );
    }

    public void deletar(Long id){
        if (!clienteRepository.deletarPorId(id)){
            throw new RuntimeException("ID não encontrado. Não foi possível excluir o Cliente.");
        }
    }
}
