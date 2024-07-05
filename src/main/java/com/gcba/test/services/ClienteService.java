package com.gcba.test.services;

import com.gcba.test.entities.Cliente;

import java.util.List;

public interface ClienteService {
    Cliente create(Cliente cliente);
    void delete(Long id);
    List<Cliente> getAll();
    Cliente getById(Long id);
}
