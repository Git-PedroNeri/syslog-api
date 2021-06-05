package com.syslog.api.domain.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.syslog.api.domain.model.StatusEntrega;

public class EntregaDTO {

	private Long id;
	private ClientePartialDTO cliente; 
	private DestinatarioDTO destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dtPedido;
	private OffsetDateTime dtFinalizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public ClientePartialDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClientePartialDTO cliente) {
		this.cliente = cliente;
	}

	public DestinatarioDTO getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(DestinatarioDTO destinatario) {
		this.destinatario = destinatario;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public StatusEntrega getStatus() {
		return status;
	}

	public void setStatus(StatusEntrega status) {
		this.status = status;
	}

	public OffsetDateTime getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(OffsetDateTime dtPedido) {
		this.dtPedido = dtPedido;
	}

	public OffsetDateTime getDtFinalizacao() {
		return dtFinalizacao;
	}

	public void setDtFinalizacao(OffsetDateTime dtFinalizacao) {
		this.dtFinalizacao = dtFinalizacao;
	}

}
