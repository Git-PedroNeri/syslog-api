package com.syslog.api.domain.model.form;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EntregaForm {

	@Valid
	@NotNull
	private ClienteIdForm cliente;
	@NotNull
	@Valid
	private DestinatarioForm destinatario;
	@NotNull
	private BigDecimal taxa;

	public ClienteIdForm getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIdForm cliente) {
		this.cliente = cliente;
	}

	public DestinatarioForm getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(DestinatarioForm destinatario) {
		this.destinatario = destinatario;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

}
