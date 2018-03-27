package br.com.jogo.gourmet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe motor para executar e controlar o jogo gourmet
 * 
 * @author andre.lermen
 *
 */
public class CriarGourmet implements Serializable {

	private static final long serialVersionUID = 8864098485964931435L;

	private JFrame frame;

	private Map<String, String> pratosPreferidos;

	private String pratoRuim = "";

	public CriarGourmet() {
		this.pratosPreferidos = new HashMap<String, String>();

		this.initFrame();

	}

	/**
	 * Método que da inicio ao jogo
	 * 
	 * @author andre.lermen
	 *
	 */
	private void initGame() {
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

			this.obterPratoPreferido();

			resultConfirm = JOptionPane.showConfirmDialog(this.frame, GourmetConstantes.MSG_BOLO_CHOCOLATE);

			if (resultConfirm == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(this.frame, GourmetConstantes.MSG_ACERTEI);
			} else {
				this.pratoRuim = GourmetConstantes.BOLO_CHOCOLATE;

			}
		}
		return resultConfirm;
	}

	/**
	 * Método que recupera o prato preferido.
	 * 
	 */
	private void obterPratoPreferido() {
		int resultConfirm;
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

	/**
	 * Método que cria e inicializa as configurações do game
	 * 
	 * @author andre.lermen
	 *
	 */
	private void initFrame() {
		this.frame = new JFrame(GourmetConstantes.MSG_TITULO);
		this.frame.setSize(250, 180);
		this.frame.setVisible(true);
		this.frame.setLocationRelativeTo(null);

		JLabel label = new JLabel(GourmetConstantes.MSG_DIALOGO_GOSTO);

		JButton button = new JButton(GourmetConstantes.OK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initGame();
			}
		});

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		JPanel panelLabel = this.obterPanelLabel();

		JPanel panelButton = this.obterPanelButton();

		panelLabel.add(label, gridBagConstraints);
		panelButton.add(button, gridBagConstraints);

		this.frame.getContentPane().add(panelLabel);
		this.frame.getContentPane().add(panelButton);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * Cria a panel para a label inicial do jogo.
	 * 
	 * @return JPanel
	 */
	private JPanel obterPanelButton() {
		JPanel panelButton = new JPanel();
		panelButton.setSize(50, 50);
		panelButton.setLayout(new GridBagLayout());
		return panelButton;
	}

	/**
	 *  Cria a panel para o botão de start do jogo.
	 *  
	 * @return JPanel
	 */
	private JPanel obterPanelLabel() {
		JPanel panelLabel = new JPanel();
		panelLabel.setSize(250, 50);
		panelLabel.setLayout(new GridBagLayout());
		return panelLabel;
	}
}
