package com.syslog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.syslog.api.domain.model.Entrega;
import com.syslog.api.domain.model.dto.EntregaDTO;
import com.syslog.api.domain.model.form.EntregaForm;

import lombok.AllArgsConstructor;

/**
 * 
 * Classe Responsável por mapear a montagem de objetos convertendo de um tipo
 * para o outro
 * 
 * @author pedro.neri
 *
 */

@Component
public class EntregaMapper {

	ModelMapper modelMapper;

	public EntregaMapper(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}

	public EntregaDTO toDTO(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}

	public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas) {
		return entregas.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Entrega toEntity(EntregaForm entregaForm) {
		return modelMapper.map(entregaForm, Entrega.class);
	}

}
