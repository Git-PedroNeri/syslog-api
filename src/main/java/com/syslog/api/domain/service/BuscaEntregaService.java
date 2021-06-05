package com.syslog.api.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syslog.api.domain.exception.ExceptionBussiness;
import com.syslog.api.domain.model.Entrega;
import com.syslog.api.domain.repository.EntregaRepository;

@Service
public class BuscaEntregaService {

	@Autowired
	EntregaRepository entregaRepository;

	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));
	}

}
