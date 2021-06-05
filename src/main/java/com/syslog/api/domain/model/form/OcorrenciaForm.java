package com.syslog.api.domain.model.form;

import javax.validation.constraints.NotBlank;

public class OcorrenciaForm {
	@NotBlank
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
