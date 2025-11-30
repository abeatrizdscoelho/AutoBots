package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@Service
public class EnderecoServico {
    
    @Autowired
    private EnderecoRepositorio repositorio;

    public List<Endereco> listar() {
        return repositorio.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        return repositorio.save(endereco);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }
}
