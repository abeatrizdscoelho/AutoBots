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

import com.autobots.automanager.dtos.EnderecoDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelos.AdicionadorLinkEndereco;
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
    private AdicionadorLinkEndereco adicionadorLink;

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> obterEnderecoPorId(@PathVariable long id) {
        Optional<Endereco> enderecoOptional = enderecoServico.buscarPorId(id);
        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            adicionadorLink.adicionarLink(endereco);
            ResponseEntity<Endereco> resposta = new ResponseEntity<>(endereco, HttpStatus.FOUND);
            return resposta;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Endereco>> obterEnderecos() {
        List<Endereco> enderecos = enderecoServico.listar();
        if (enderecos.isEmpty()) {
            ResponseEntity<List<Endereco>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(enderecos);
			ResponseEntity<List<Endereco>> resposta = new ResponseEntity<>(enderecos, HttpStatus.FOUND);
			return resposta;
        }
    }

    @PostMapping("/cadastrar/{idCliente}")
    public ResponseEntity<Endereco> cadastrarEndereco(@PathVariable Long idCliente, @Valid @RequestBody EnderecoDTO dto) {
        Cliente cliente = clienteServico.buscarPorId(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Endereco endereco = new Endereco();
        endereco.setRua(dto.getRua());
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        endereco.setNumero(dto.getNumero());
        endereco.setCodigoPostal(dto.getCodigoPostal());
        endereco.setInformacoesAdicionais(dto.getInformacoesAdicionais());
        endereco.setCliente(cliente);
        cliente.setEndereco(endereco);

        Endereco enderecoSalvo = enderecoServico.salvar(endereco);
        adicionadorLink.adicionarLink(enderecoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvo);
    }

    @PutMapping("/atualizar/{id}")
	public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoDTO dto) {
		Optional<Endereco> enderecoOptional = enderecoServico.buscarPorId(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Endereco endereco = enderecoOptional.get();
        Endereco atualizacao = new Endereco();
        atualizacao.setRua(dto.getRua());
        atualizacao.setEstado(dto.getEstado());
        atualizacao.setCidade(dto.getCidade());
        atualizacao.setBairro(dto.getBairro());
        atualizacao.setNumero(dto.getNumero());
        atualizacao.setCodigoPostal(dto.getCodigoPostal());
        atualizacao.setInformacoesAdicionais(dto.getInformacoesAdicionais());

		EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(endereco, atualizacao);
        Endereco enderecoSalvo = enderecoServico.salvar(endereco);
        adicionadorLink.adicionarLink(enderecoSalvo);
        return ResponseEntity.ok(enderecoSalvo);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        Optional<Endereco> enderecoOptional = enderecoServico.buscarPorId(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        enderecoServico.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
