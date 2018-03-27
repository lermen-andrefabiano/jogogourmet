package br.com.jogo.gourmet;

import java.io.Serializable;

import br.com.jogo.gourmet.acao.AcaoGourmet;
import br.com.jogo.gourmet.acao.AcaoMotorJogo;
import br.com.jogo.gourmet.acao.AcaoStartFrame;

/**
 * Classe mestre para iniciar o jogo gourmet
 * 
 * @author andre.lermen
 *
 */
public class GourmetMain implements Serializable {

	private static final long serialVersionUID = -8331285322591800101L;

	public static void main(String[] args) {

		AcaoGourmet acaoStartFrame = new AcaoStartFrame();
		AcaoGourmet acaoMotorJogo = new AcaoMotorJogo();
		
		acaoStartFrame.addProximo(acaoMotorJogo);
		acaoStartFrame.executar();
	}
}
