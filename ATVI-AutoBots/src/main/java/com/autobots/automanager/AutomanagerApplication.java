package com.autobots.automanager;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@SpringBootApplication
public class AutomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomanagerApplication.class, args);
	}

	@Component
	public static class Runner implements ApplicationRunner {
		@Autowired
		public ClienteRepositorio repositorio;

		@Override
		public void run(ApplicationArguments args) throws Exception {
			Calendar calendario = Calendar.getInstance();
			calendario.set(2006, 8, 22);

			Cliente cliente = new Cliente();
			cliente.setNome("Tom Cruise");
			cliente.setNomeSocial("Tom");
			cliente.setDataNascimento(calendario.getTime());
			cliente.setDataCadastro(Calendar.getInstance().getTime());

			Telefone telefone = new Telefone();
			telefone.setDdd("12");
			telefone.setNumero("992009902");
			telefone.setCliente(cliente);
			cliente.getTelefones().add(telefone);

			Endereco endereco = new Endereco();
			endereco.setEstado("São Paulo");
			endereco.setCidade("São José dos Campos");
			endereco.setBairro("Jardim Aquarius");
			endereco.setRua("Avenida Cidade Jardim");
			endereco.setNumero("26");
			endereco.setCodigoPostal("12240001");
			endereco.setInformacoesAdicionais("Apartamento");
			endereco.setCliente(cliente);
			cliente.setEndereco(endereco);

			Documento rg = new Documento();
			rg.setTipo("RG");
			rg.setNumero("624874609");
			rg.setCliente(cliente);

			Documento cpf = new Documento();
			cpf.setTipo("CPF");
			cpf.setNumero("65054281887");
			cpf.setCliente(cliente);

			cliente.getDocumentos().add(rg);
			cliente.getDocumentos().add(cpf);

			repositorio.save(cliente);
		}
	}
}
