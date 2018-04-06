package br.com.jogo.gourmet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prato implements Serializable {

	private static final long serialVersionUID = -549147362813395895L;

	private String descricao;

	private String caracteristica;
	
	private List<String> subPrato;

	public Prato() {
	}

	public Prato(String descricao, String caracteristica) {
		this.descricao = descricao;
		this.caracteristica = caracteristica;
		this.subPrato = new ArrayList<>();
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaracteristica() {
		return caracteristica;
	}
	
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	public void setSubPrato(List<String> subPrato) {
		this.subPrato = subPrato;
	}
	
	public List<String> getSubPrato() {
		return subPrato;
	}

}
