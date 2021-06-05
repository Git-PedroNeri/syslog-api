package com.syslog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.syslog.api.assembler.EntregaMapper;
import com.syslog.api.domain.model.Entrega;
import com.syslog.api.domain.model.dto.EntregaDTO;
import com.syslog.api.domain.model.form.EntregaForm;
import com.syslog.api.domain.repository.EntregaRepository;
import com.syslog.api.domain.service.FinalizacaoEntregaService;
import com.syslog.api.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;
	@Autowired
	private EntregaRepository entregaRepository;
	@Autowired
	private EntregaMapper entregaMapper;
	@Autowired
	private FinalizacaoEntregaService finalizacaoEntregaService;

	@GetMapping
	public List<EntregaDTO> listar() {
		return entregaMapper.toCollectionDTO(entregaRepository.findAll());
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId).map(entrega -> {
			EntregaDTO entregaDTO = entregaMapper.toDTO(entrega);
			return ResponseEntity.ok(entregaDTO);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping(value = "/solicitar")
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitarEntrega(@Valid @RequestBody EntregaForm entregaForm) {
		Entrega entrega = entregaMapper.toEntity(entregaForm);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(entrega);
		return entregaMapper.toDTO(entregaSolicitada);
	}

	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}

}
