package com.gcba.test.services;

import com.gcba.test.entities.Cliente;
import com.gcba.test.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente create(Cliente cliente) {
        Cliente cl = clienteRepository.save(cliente);
        return cl;
    }

    @Override
    public void delete(Long id) {
        Cliente cl = clienteRepository.findById(id).orElse(null);
        clienteRepository.delete(cl);
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
}
