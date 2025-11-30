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

import com.autobots.automanager.dtos.DocumentoDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.AdicionadorLinkDocumento;
import com.autobots.automanager.modelos.DocumentoAtualizador;
import com.autobots.automanager.servicos.ClienteServico;
import com.autobots.automanager.servicos.DocumentoServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

    @Autowired
    private DocumentoServico documentoServico;
    
    @Autowired
    private AdicionadorLinkDocumento adicionadorLink;

    @Autowired
    private ClienteServico clienteServico;

    @GetMapping("/{id}")
    public ResponseEntity<Documento> obterDocumentoPorId(@PathVariable long id) {
        Optional<Documento> documentoOptional = documentoServico.buscarPorId(id);
        if (documentoOptional.isPresent()) {
            Documento documento = documentoOptional.get();
            adicionadorLink.adicionarLink(documento);
            ResponseEntity<Documento> resposta = new ResponseEntity<>(documento, HttpStatus.FOUND);
            return resposta;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Documento>> obterDocumentos() {
        List<Documento> documentos = documentoServico.listar();
        if (documentos.isEmpty()) {
            ResponseEntity<List<Documento>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLink.adicionarLink(documentos);
			ResponseEntity<List<Documento>> resposta = new ResponseEntity<>(documentos, HttpStatus.FOUND);
			return resposta;
        }
    }

    @PostMapping("/cadastrar/{idCliente}")
    public ResponseEntity<Documento> cadastrarDocumento(@PathVariable Long idCliente, @Valid @RequestBody DocumentoDTO dto) {
        Cliente cliente = clienteServico.buscarPorId(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Documento documento = new Documento();
        documento.setTipo(dto.getTipo());
        documento.setNumero(dto.getNumero());
        documento.setCliente(cliente);
        cliente.getDocumentos().add(documento);

        Documento documentoSalvo = documentoServico.salvar(documento);
        adicionadorLink.adicionarLink(documentoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(documentoSalvo);
    }

    @PutMapping("/atualizar/{id}")
	public ResponseEntity<Documento> atualizarDocumento(@PathVariable Long id, @Valid @RequestBody DocumentoDTO dto) {
		Optional<Documento> documentoOptional = documentoServico.buscarPorId(id);
        if (documentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Documento documento = documentoOptional.get();
        Documento atualizacao = new Documento();
        atualizacao.setTipo(dto.getTipo());
        atualizacao.setNumero(dto.getNumero());

		DocumentoAtualizador atualizador = new DocumentoAtualizador();
        atualizador.atualizar(documento, atualizacao);
        Documento documentoSalvo = documentoServico.salvar(documento);
        adicionadorLink.adicionarLink(documentoSalvo);
        return ResponseEntity.ok(documentoSalvo);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deletarDocumento(@PathVariable Long id) {
        Optional<Documento> documentoOptional = documentoServico.buscarPorId(id);
        if (documentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        documentoServico.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
