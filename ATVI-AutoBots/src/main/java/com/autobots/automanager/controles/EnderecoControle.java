package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dtos.EnderecoDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelos.EnderecoAtualizador;
import com.autobots.automanager.servicos.ClienteServico;
import com.autobots.automanager.servicos.EnderecoServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {

    @Autowired
    private EnderecoServico enderecoServico;

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping("/{id}")
    public Optional<Endereco> obterEndereco(@PathVariable Long id) {
        return enderecoServico.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<Endereco> listarEnderecos() {
        return enderecoServico.listar();
    }

    @PostMapping("/cadastrar/{idCliente}")
    public Endereco cadastrarEndereco(@PathVariable Long idCliente, @Valid @RequestBody EnderecoDTO dto) {
        Cliente cliente = clienteServico.buscarPorId(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Endereco endereco = new Endereco();
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setCodigoPostal(dto.getCodigoPostal());
        endereco.setInformacoesAdicionais(dto.getInformacoesAdicionais());
        endereco.setCliente(cliente);
        return enderecoServico.salvar(endereco);
    }

    @PutMapping("/atualizar/{id}")
    public Endereco atualizarEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoDTO dto) {
        Endereco endereco = enderecoServico.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Endereco atualizacao = new Endereco();
        atualizacao.setEstado(dto.getEstado());
        atualizacao.setCidade(dto.getCidade());
        atualizacao.setBairro(dto.getBairro());
        atualizacao.setRua(dto.getRua());
        atualizacao.setNumero(dto.getNumero());
        atualizacao.setCodigoPostal(dto.getCodigoPostal());
        atualizacao.setInformacoesAdicionais(dto.getInformacoesAdicionais());

        EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(endereco, atualizacao);

        return enderecoServico.salvar(endereco);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarEndereco(@PathVariable Long id) {
        enderecoServico.deletar(id);
    }
}
