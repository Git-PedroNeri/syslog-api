package com.syslog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.syslog.api.domain.model.Entrega;
import com.syslog.api.domain.model.Ocorrencia;
import com.syslog.api.domain.model.dto.EntregaDTO;
import com.syslog.api.domain.model.dto.OcorrenciaDTO;
import com.syslog.api.domain.model.form.OcorrenciaForm;

/**
 * 
 * Classe Responsável por mapear a montagem de objetos convertendo de um tipo
 * para o outro
 * 
 * @author pedro.neri
 *
 */

@Component
public class OcorrenciaMapper {

	ModelMapper modelMapper;

	public OcorrenciaMapper(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}

	public List<OcorrenciaDTO> toCollectionDTO(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Entrega toEntity(OcorrenciaForm ocorrenciaForm) {
		return modelMapper.map(ocorrenciaForm, Entrega.class);
	}

}
