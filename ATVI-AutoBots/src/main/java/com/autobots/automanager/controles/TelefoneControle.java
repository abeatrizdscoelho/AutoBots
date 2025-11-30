package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dtos.TelefoneDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.TelefoneAtualizador;
import com.autobots.automanager.servicos.ClienteServico;
import com.autobots.automanager.servicos.TelefoneServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

    @Autowired
    private TelefoneServico telefoneServico;

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping("/{id}")
    public Optional<Telefone> obterTelefone(@PathVariable Long id) {
        return telefoneServico.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<Telefone> listarTelefones() {
        return telefoneServico.listar();
    }

    @PostMapping("/cadastrar/{idCliente}")
    public Telefone cadastrarTelefone(@PathVariable Long idCliente, @Valid @RequestBody TelefoneDTO dto) {
        Cliente cliente = clienteServico.buscarPorId(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Telefone telefone = new Telefone();
        telefone.setDdd(dto.getDdd());
        telefone.setNumero(dto.getNumero());
        telefone.setCliente(cliente);
        return telefoneServico.salvar(telefone);
    }

    @PutMapping("/atualizar/{id}")
    public Telefone atualizarTelefone(@PathVariable Long id, @Validated @RequestBody TelefoneDTO dto) {
        Telefone telefone = telefoneServico.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));

        Telefone atualizacao = new Telefone();
        atualizacao.setDdd(dto.getDdd());
        atualizacao.setNumero(dto.getNumero());

        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);

        return telefoneServico.salvar(telefone);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarTelefone(@PathVariable Long id) {
        telefoneServico.deletar(id);
    }
}
