package com.syslog.api.domain.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DestinatarioForm {

	@NotBlank
	@NotNull
	private String nome;
	@NotNull
	@NotBlank
	private String logradouro;
	@NotNull
	@NotBlank
	private String numero;
	@NotNull
	@NotBlank
	private String complemento;
	@NotNull
	@NotBlank
	private String bairro;
	@NotNull
	@NotBlank
	private String cep;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
