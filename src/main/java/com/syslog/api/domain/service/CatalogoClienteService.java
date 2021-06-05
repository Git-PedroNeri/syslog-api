package com.syslog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syslog.api.domain.exception.ExceptionBussiness;
import com.syslog.api.domain.model.Cliente;
import com.syslog.api.domain.repository.ClienteRepository;

@Service
public class CatalogoClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ExceptionBussiness("Cliente Não Encontrado"));
	}

	@Transactional // Garante o rollback
	public Cliente cadastrar(Cliente cliente) {

		boolean emailInUse = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

		if (emailInUse) {
			throw new ExceptionBussiness("Já existe um Cliente com esse e-mail");
		}

		return clienteRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}

}
