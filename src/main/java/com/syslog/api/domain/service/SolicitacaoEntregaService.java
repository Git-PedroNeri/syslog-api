package com.syslog.api.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syslog.api.domain.model.Cliente;
import com.syslog.api.domain.model.Entrega;
import com.syslog.api.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {

	@Autowired
	CatalogoClienteService catalogoCLienteService;
	@Autowired
	EntregaRepository entregaRepository;

	@Transactional
	public Entrega solicitar(Entrega entrega) {

		Cliente cliente = catalogoCLienteService.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setDtPedido(OffsetDateTime.now());

		return entregaRepository.save(entrega);

	}

}
