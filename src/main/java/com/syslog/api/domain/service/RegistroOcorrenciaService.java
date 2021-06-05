package com.syslog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syslog.api.domain.model.Entrega;
import com.syslog.api.domain.model.Ocorrencia;

@Service
public class RegistroOcorrenciaService {

	@Autowired
	private BuscaEntregaService buscaEntregaService;

	/**
	 * Metodo que registra uma ocorrencia
	 * 
	 * @return
	 */
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		return entrega.addOcorrencia(descricao);
	}

}
