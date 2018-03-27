package br.com.jogo.gourmet.acao;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import br.com.jogo.gourmet.GourmetConstantes;

public class AcaoMotorJogo extends AcaoGourmet {

	private static final long serialVersionUID = 829495833974179674L;

	private Map<String, String> pratosPreferidos = new HashMap<String, String>();

	private String pratoRuim = "";

	@Override
	public void executar() {

		this.executarMotor();

		super.executar();
	}

	private void executarMotor() {
		int resultConfirm = this.entradaPratoDefault();

		this.pratoPreferido(resultConfirm);

	}

	/**
	 * Método responsável por definir os pratos padrões do jogo.
	 * 
	 * @return 10442
	 */
	private int entradaPratoDefault() {
		int resultConfirm = JOptionPane.showConfirmDialog(this.frame, GourmetConstantes.MSG_PRATO_MASSA);

		if (resultConfirm == JOptionPane.YES_OPTION) {
			resultConfirm = JOptionPane.showConfirmDialog(this.frame, GourmetConstantes.MSG_PRATO_LASANHA);

			if (resultConfirm == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(this.frame, GourmetConstantes.MSG_ACERTEI);
			} else {
				this.pratoRuim = GourmetConstantes.LASANHA;
			}

		} else {

			resultConfirm = this.obterPratoPreferido(resultConfirm);

			if (resultConfirm != 0) {
				resultConfirm = this.pratoBoloChocolate();
			}

		}
		return resultConfirm;
	}

	private int pratoBoloChocolate() {
		int resultConfirm;
		resultConfirm = JOptionPane.showConfirmDialog(this.frame, GourmetConstantes.MSG_BOLO_CHOCOLATE);

		if (resultConfirm == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this.frame, GourmetConstantes.MSG_ACERTEI);
		} else {
			this.pratoRuim = GourmetConstantes.BOLO_CHOCOLATE;

		}
		return resultConfirm;
	}

	/**
	 * Método que recupera o prato preferido.
	 * 
	 */
	private int obterPratoPreferido(int resultConfirm) {
		if (!this.pratosPreferidos.isEmpty()) {
			for (Map.Entry<String, String> entry : this.pratosPreferidos.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();

				resultConfirm = JOptionPane.showConfirmDialog(this.frame,
						String.format(GourmetConstantes.MSG_PRATO_PENSOU, value));

				if (resultConfirm == JOptionPane.YES_OPTION) {

					resultConfirm = JOptionPane.showConfirmDialog(this.frame,
							String.format(GourmetConstantes.MSG_PRATO_PENSOU, key));

					if (resultConfirm == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(this.frame, GourmetConstantes.MSG_ACERTEI);
						break;
					}
				}
			}
		}
		return resultConfirm;
	}

	/**
	 * Método que armazena o prato preferido
	 * 
	 * @author andre.lermen
	 *
	 */
	private void pratoPreferido(int resultConfirm) {
		if (resultConfirm != 0) {
			String prato = JOptionPane.showInputDialog(this.frame, GourmetConstantes.MSG_QUAL_PRATO);

			String tipoPrato = JOptionPane.showInputDialog(this.frame,
					String.format(GourmetConstantes.MSG_TIPO_PRATO, prato, this.pratoRuim));

			this.pratosPreferidos.put(prato, tipoPrato);
		}
	}

}
