package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Mercadoria;
import com.autobots.automanager.repositories.MercadoriaRepository;
import com.autobots.automanager.utils.AuthUtil;

@Service
public class MercadoriaService {

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private MercadoriaRepository repository;

    public List<Mercadoria> listar() {
        return repository.findAll();
    }

    public Optional<Mercadoria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Mercadoria salvar(Mercadoria mercadoria) {
        Empresa empresa = authUtil.getEmpresaLogada();
        mercadoria.setEmpresa(empresa);
        empresa.getMercadorias().add(mercadoria);
        return repository.save(mercadoria);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
