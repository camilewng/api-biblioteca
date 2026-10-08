package ctw.mi81.api_biblioteca.repository;

import ctw.mi81.api_biblioteca.model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepository {

    private final List<Cliente> clientes = new ArrayList<>();
    private final AtomicLong sequencialId = new AtomicLong(1);

    public Cliente salvar(Cliente cliente){

        if (cliente.getId() == null){
            cliente.setId(sequencialId.getAndIncrement());
        } else {
            deletarPorId(cliente.getId());
        }
        clientes.add(cliente);
        return cliente;
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public List<Cliente> listarTodos(){
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> buscarPorCpf(String cpf){
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    public boolean deletarPorId(Long id){
        return clientes.removeIf(c -> c.getId().equals(id));
    }
}
