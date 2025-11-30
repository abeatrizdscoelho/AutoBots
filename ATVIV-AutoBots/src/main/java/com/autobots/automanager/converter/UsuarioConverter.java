package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.UsuarioDTO;
import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Usuario;

@Component
public class UsuarioConverter {

    public UsuarioDTO converterParaDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setNomeSocial(usuario.getNomeSocial());
        dto.setPerfis(usuario.getPerfis());
        dto.setTelefones(usuario.getTelefones());
        dto.setEndereco(usuario.getEndereco());
        dto.setDocumentos(usuario.getDocumentos());
        dto.setEmails(usuario.getEmails());
        dto.setCredencial(usuario.getCredencial());
        dto.setMercadorias(usuario.getMercadorias());
        dto.setVendas(usuario.getVendas());
        dto.setVeiculos(usuario.getVeiculos());

        if (usuario.getEmpresa() != null) {
            dto.setEmpresaId(usuario.getEmpresa().getId());
        }

        return dto;
    }

    public Usuario converterParaEntidade(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setNomeSocial(dto.getNomeSocial());
        usuario.setPerfis(dto.getPerfis());
        usuario.setTelefones(dto.getTelefones());
        usuario.setEndereco(dto.getEndereco());
        usuario.setDocumentos(dto.getDocumentos());
        usuario.setEmails(dto.getEmails());
        usuario.setCredencial(dto.getCredencial());
        usuario.setMercadorias(dto.getMercadorias());
        usuario.setVendas(dto.getVendas());
        usuario.setVeiculos(dto.getVeiculos());

        if (dto.getEmpresaId() != null) {
            Empresa empresa = new Empresa();
            empresa.setId(dto.getEmpresaId());
            usuario.setEmpresa(empresa);
        }

        return usuario;
    }

    public List<UsuarioDTO> converterParaListaDTO(List<Usuario> usuarios) {
        return usuarios.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
