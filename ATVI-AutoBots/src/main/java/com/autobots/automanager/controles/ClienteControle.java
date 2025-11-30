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

import com.autobots.automanager.dtos.ClienteDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelos.ClienteAtualizador;
import com.autobots.automanager.servicos.ClienteServico;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping("/{id}")
    public Optional<Cliente> obterCliente(@PathVariable Long id) {
        return clienteServico.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<Cliente> listarClientes() {
        return clienteServico.listar();
    }

    @PostMapping("/cadastrar")
    public Cliente cadastrarCliente(@Validated @RequestBody ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setNomeSocial(dto.getNomeSocial());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setDataCadastro(dto.getDataCadastro());
        return clienteServico.salvar(cliente);
    }

    @PutMapping("/atualizar/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @Validated @RequestBody ClienteDTO dto) {
        Cliente cliente = clienteServico.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Cliente atualizacao = new Cliente();
        atualizacao.setNome(dto.getNome());
        atualizacao.setNomeSocial(dto.getNomeSocial());
        atualizacao.setDataNascimento(dto.getDataNascimento());
        atualizacao.setDataCadastro(dto.getDataCadastro());

        ClienteAtualizador atualizador = new ClienteAtualizador();
        atualizador.atualizar(cliente, atualizacao);
        return clienteServico.salvar(cliente);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarCliente(@PathVariable Long id) {
        clienteServico.deletar(id);
    }
}
