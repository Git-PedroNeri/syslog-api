package com.syslog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.syslog.api.assembler.OcorrenciaMapper;
import com.syslog.api.domain.model.Entrega;
import com.syslog.api.domain.model.Ocorrencia;
import com.syslog.api.domain.model.dto.OcorrenciaDTO;
import com.syslog.api.domain.model.form.OcorrenciaForm;
import com.syslog.api.domain.service.BuscaEntregaService;
import com.syslog.api.domain.service.RegistroOcorrenciaService;

@RestController
@RequestMapping("entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	@Autowired
	private RegistroOcorrenciaService registroOcorrenciaService;
	@Autowired
	private OcorrenciaMapper ocorrenciaMapper;
	@Autowired
	private BuscaEntregaService buscaEntregaService;

	@GetMapping
	public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		List<Ocorrencia> ocorrencias = entrega.getOcorrencias();
		return ocorrenciaMapper.toCollectionDTO(ocorrencias);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaForm ocorrenciaForm) {
		Ocorrencia registrar = registroOcorrenciaService.registrar(entregaId, ocorrenciaForm.getDescricao());
		OcorrenciaDTO ocorrenciaDTO = ocorrenciaMapper.toDTO(registrar);
		return ocorrenciaDTO;

	}

}
