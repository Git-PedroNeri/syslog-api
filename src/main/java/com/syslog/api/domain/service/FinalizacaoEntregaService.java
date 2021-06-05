package com.syslog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syslog.api.domain.model.Entrega;
import com.syslog.api.domain.repository.EntregaRepository;

@Service
public class FinalizacaoEntregaService {
	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	BuscaEntregaService buscaEntregaService;

	@Transactional
	public void finalizar(Long entregaId) {

		Entrega entrega = buscaEntregaService.buscar(entregaId);

		entrega.finalizar();

		entregaRepository.save(entrega);

	}

}
