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

import com.autobots.automanager.dtos.TelefoneDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.AdicionadorLinkTelefone;
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
    private AdicionadorLinkTelefone adicionadorLink;

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> obterTelefonePorId(@PathVariable long id) {
        Optional<Telefone> telefoneOptional = telefoneServico.buscarPorId(id);
        if (telefoneOptional.isPresent()) {
            Telefone telefone = telefoneOptional.get();
            adicionadorLink.adicionarLink(telefone);
            ResponseEntity<Telefone> resposta = new ResponseEntity<>(telefone, HttpStatus.FOUND);
            return resposta;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Telefone>> obterTelefones() {
        List<Telefone> telefones = telefoneServico.listar();
        if (telefones.isEmpty()) {
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(telefones);
			ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(telefones, HttpStatus.FOUND);
			return resposta;
        }
    }

    @PostMapping("/cadastrar/{idCliente}")
    public ResponseEntity<Telefone> cadastrarTelefone(@PathVariable Long idCliente, @Valid @RequestBody TelefoneDTO dto) {
        Cliente cliente = clienteServico.buscarPorId(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Telefone telefone = new Telefone();
        telefone.setDdd(dto.getDdd());
        telefone.setNumero(dto.getNumero());
        telefone.setCliente(cliente);
        cliente.getTelefones().add(telefone);

        Telefone telefoneSalvo = telefoneServico.salvar(telefone);
        adicionadorLink.adicionarLink(telefoneSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneSalvo);
    }

    @PutMapping("/atualizar/{id}")
	public ResponseEntity<Telefone> atualizarTelefone(@PathVariable Long id, @Valid @RequestBody TelefoneDTO dto) {
		Optional<Telefone> telefoneOptional = telefoneServico.buscarPorId(id);
        if (telefoneOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Telefone telefone = telefoneOptional.get();
        Telefone atualizacao = new Telefone();
        atualizacao.setDdd(dto.getDdd());
        atualizacao.setNumero(dto.getNumero());

		TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);
        Telefone telefoneSalvo = telefoneServico.salvar(telefone);
        adicionadorLink.adicionarLink(telefoneSalvo);
        return ResponseEntity.ok(telefoneSalvo);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarTelefone(@PathVariable Long id) {
        Optional<Telefone> telefoneOptional = telefoneServico.buscarPorId(id);
        if (telefoneOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        telefoneServico.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
