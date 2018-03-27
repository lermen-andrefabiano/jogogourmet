package br.com.jogo.gourmet.acao;

import java.io.Serializable;

import javax.swing.JFrame;

public abstract class AcaoGourmet implements Serializable {

	private static final long serialVersionUID = -8671723037278680115L;
	
	protected JFrame frame;

	protected AcaoGourmet prox;

	public void executar() {
		if (this.prox != null) {
			this.prox.executar();
		}
	}

	public AcaoGourmet addProximo(AcaoGourmet prox) {
		this.prox = prox;

		return this.prox;
	}
}
