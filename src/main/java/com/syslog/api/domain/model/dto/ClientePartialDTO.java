package com.syslog.api.domain.model.dto;

/**
 * 
 * Classe que representa um Cliente personalizado
 * 
 * @author pedro.neri
 *
 */
public class ClientePartialDTO {

	private Long id;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
