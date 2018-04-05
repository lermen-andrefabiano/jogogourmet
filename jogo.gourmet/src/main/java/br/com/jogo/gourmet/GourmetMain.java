package br.com.jogo.gourmet;

import java.io.Serializable;

/**
 * Classe mestre para iniciar o jogo gourmet
 * 
 * @author andre.lermen
 *
 */
public class GourmetMain implements Serializable {

	private static final long serialVersionUID = -8331285322591800101L;

	public static void main(String[] args) {

		new GourmetMotor().start();

	}

}
