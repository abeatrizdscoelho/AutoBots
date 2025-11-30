package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@Service
public class ClienteServico {
    
    @Autowired
    private ClienteRepositorio repositorio;

    public List<Cliente> listar() {
        return repositorio.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        return repositorio.save(cliente);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }
}
