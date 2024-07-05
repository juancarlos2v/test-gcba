package com.gcba.test.controllers;

import com.gcba.test.entities.Cliente;
import com.gcba.test.services.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Object> getCliente(@RequestBody Cliente cliente) {
        clienteService.create(cliente);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado");
    }

    @GetMapping
    public List<Cliente> getAllClients(){
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>  deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente eliminado");
    }

}
