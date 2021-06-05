package com.syslog.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sun.istack.NotNull;
import com.syslog.api.domain.ValidationGroups;
import com.syslog.api.domain.ValidationGroups.ClienteId;
import com.syslog.api.domain.exception.ExceptionBussiness;

import ch.qos.logback.core.subst.Token.Type;
import lombok.Data;

@Entity
@Data
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Valid
	@NotNull
	@Embedded
	private Destinatario destinatario;

	@NotNull
	private BigDecimal taxa;

	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private StatusEntrega status = StatusEntrega.PENDENTE;

	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dtPedido;

	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dtFinalizacao;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();

	public Entrega() {
		super();
	}

	public Entrega(@Valid @ConvertGroup(from = Default.class, to = ClienteId.class) Cliente cliente,
			@Valid Destinatario destinatario, BigDecimal taxa, StatusEntrega status, OffsetDateTime dtPedido,
			OffsetDateTime dtFinalizacao) {
		super();
		this.cliente = cliente;
		this.destinatario = destinatario;
		this.taxa = taxa;
		this.status = status;
		this.dtPedido = dtPedido;
		this.dtFinalizacao = dtFinalizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
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

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrega other = (Entrega) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", cliente=" + cliente + ", destinatario=" + destinatario + ", taxa=" + taxa
				+ ", status=" + status + ", dtPedido=" + dtPedido + ", dtFinalizacao=" + dtFinalizacao + "]";
	}

	public Ocorrencia addOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);

		this.getOcorrencias().add(ocorrencia);

		return ocorrencia;

	}

	public void finalizar() {
		
		if(!isFinishable()) {
			throw new ExceptionBussiness("Entrega não pode ser finalizavel");
		}

		setStatus(StatusEntrega.FINALIZADA);
		setDtFinalizacao(OffsetDateTime.now());
		
	}

	public Boolean isFinishable() {
		return StatusEntrega.PENDENTE.equals(getStatus());
	}

}
