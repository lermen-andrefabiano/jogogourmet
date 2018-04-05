package br.com.jogo.gourmet.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prato implements Serializable {

	private static final long serialVersionUID = -549147362813395895L;

	private String prato;

	private String tipoPrato;
	
	private List<String> subPrato;

	public Prato() {
	}

	public Prato(String prato, String tipoPrato) {
		this.prato = prato;
		this.tipoPrato = tipoPrato;
		this.subPrato = new ArrayList<>();
	}

	public String getPrato() {
		return prato;
	}

	public void setPrato(String prato) {
		this.prato = prato;
	}

	public String getTipoPrato() {
		return tipoPrato;
	}

	public void setTipoPrato(String tipoPrato) {
		this.tipoPrato = tipoPrato;
	}
	
	public void setSubPrato(List<String> subPrato) {
		this.subPrato = subPrato;
	}
	
	public List<String> getSubPrato() {
		return subPrato;
	}

}
