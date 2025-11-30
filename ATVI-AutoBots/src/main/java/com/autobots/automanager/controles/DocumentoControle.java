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

import com.autobots.automanager.dtos.DocumentoDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
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
    private ClienteServico clienteServico;

    @GetMapping("/{id}")
    public Optional<Documento> obterDocumento(@PathVariable Long id) {
        return documentoServico.buscarPorId(id);
    }

    @GetMapping("/listar")
    public List<Documento> listarDocumentos() {
        return documentoServico.listar();
    }

    @PostMapping("/cadastrar/{idCliente}")
    public Documento cadastrarDocumento(@PathVariable Long idCliente, @Valid @RequestBody DocumentoDTO dto) {
        Cliente cliente = clienteServico.buscarPorId(idCliente)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Documento documento = new Documento();
        documento.setTipo(dto.getTipo());
        documento.setNumero(dto.getNumero());
        documento.setCliente(cliente);
        return documentoServico.salvar(documento);
    }

    @PutMapping("/atualizar/{id}")
    public Documento atualizarDocumento(@PathVariable Long id, @Valid @RequestBody DocumentoDTO dto) {
        Documento documento = documentoServico.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Documento não encontrado"));

        Documento atualizacao = new Documento();
        atualizacao.setTipo(dto.getTipo());
        atualizacao.setNumero(dto.getNumero());

        DocumentoAtualizador atualizador = new DocumentoAtualizador();
        atualizador.atualizar(documento, atualizacao);
        return documentoServico.salvar(documento);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarDocumento(@PathVariable Long id) {
        documentoServico.deletar(id);
    }
}
