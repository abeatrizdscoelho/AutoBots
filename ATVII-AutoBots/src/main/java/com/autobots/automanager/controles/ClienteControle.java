package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.autobots.automanager.modelos.AdicionadorLinkCliente;
import com.autobots.automanager.modelos.ClienteAtualizador;
import com.autobots.automanager.servicos.ClienteServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {

    @Autowired
    private ClienteServico clienteServico;
    
    @Autowired
    private AdicionadorLinkCliente adicionadorLink;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterCliente(@PathVariable long id) {
        Optional<Cliente> clienteOptional = clienteServico.buscarPorId(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            adicionadorLink.adicionarLink(cliente);
            ResponseEntity<Cliente> resposta = new ResponseEntity<>(cliente, HttpStatus.FOUND);
            return resposta;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> obterClientes() {
        List<Cliente> clientes = clienteServico.listar();
        if (clientes.isEmpty()) {
            ResponseEntity<List<Cliente>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(clientes);
			ResponseEntity<List<Cliente>> resposta = new ResponseEntity<>(clientes, HttpStatus.FOUND);
			return resposta;
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setNomeSocial(dto.getNomeSocial());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setDataCadastro(dto.getDataCadastro());

        Cliente clienteSalvo = clienteServico.salvar(cliente);
        adicionadorLink.adicionarLink(clienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping("/atualizar/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
		Optional<Cliente> clienteOptional = clienteServico.buscarPorId(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Cliente cliente = clienteOptional.get();
        Cliente atualizacao = new Cliente();
        atualizacao.setNome(dto.getNome());
        atualizacao.setNomeSocial(dto.getNomeSocial());
        atualizacao.setDataNascimento(dto.getDataNascimento());
        atualizacao.setDataCadastro(dto.getDataCadastro());

		ClienteAtualizador atualizador = new ClienteAtualizador();
        atualizador.atualizar(cliente, atualizacao);
        Cliente clienteSalvo = clienteServico.salvar(cliente);
        adicionadorLink.adicionarLink(clienteSalvo);
        return ResponseEntity.ok(clienteSalvo);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteServico.buscarPorId(id);
        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        clienteServico.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
