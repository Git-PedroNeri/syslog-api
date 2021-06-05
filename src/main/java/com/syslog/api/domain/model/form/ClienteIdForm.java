package com.syslog.api.domain.model.form;

import javax.validation.constraints.NotNull;

public class ClienteIdForm {
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
