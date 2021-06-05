package com.syslog.api.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.syslog.api.domain.model.Cliente;
import com.syslog.api.domain.repository.ClienteRepository;
import com.syslog.api.domain.service.CatalogoClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

//	@Autowired 
//	ClienteRepository clienteRepository;

	/**
	 * FACILITA QUANDO FOR FAZER TESTES COM MOCK, CHAMA O CONSTRUTOR PARA INSTANCIAR
	 * UM CLIENTE REPOSITORY EM VEZ DE INJEÇÃO DE DEPENDÊNCIA
	 */
	@Autowired
	CatalogoClienteService catalogoClienteService;
	@Autowired
	ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> recuperarCliente(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrar(@RequestBody @Valid Cliente cliente) {
		return catalogoClienteService.cadastrar(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> editar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = catalogoClienteService.cadastrar(cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		catalogoClienteService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
