package br.com.jogo.gourmet;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.jogo.gourmet.model.Prato;

/**
 * Classe mestre para iniciar o jogo gourmet
 * 
 * @author andre.lermen
 *
 */
public class GourmetMotor extends JFrame implements Serializable {

	private static final long serialVersionUID = -8331285322591800101L;

	private List<Prato> pratos;

	private int resultConfirm;

	public void start() {
		this.initConfigDefault();

		this.initComponents();

	}

	public void initConfigDefault() {
		this.pratos = new ArrayList<>();

		Prato pratoMassa = new Prato("Massa", "");
		pratoMassa.getSubPrato().add("Lasanha");

		Prato pratoBolo = new Prato("Bolo de Chocolate", "");

		this.pratos.add(pratoMassa);
		this.pratos.add(pratoBolo);
	}

	/**
	 * Método responsável por definir os pratos padrões do jogo.
	 * 
	 */
	private void executarMotor() {
		int contador;

		for (contador = 0; contador <= this.pratos.size() - 1; contador++) {

			Prato prato = this.pratos.get(contador);

			this.confirmaPrato(prato.getPrato());

			if (this.resultConfirm == JOptionPane.YES_OPTION) {
				if (!prato.getSubPrato().isEmpty()) {
					for (String subPrato : prato.getSubPrato()) {
						this.confirmaPrato(subPrato);

						if (this.resultConfirm == JOptionPane.YES_OPTION) {
							acertei();
						}
					}
					break;
				} else if (!prato.getTipoPrato().isEmpty()) {
					this.confirmaPrato(prato.getTipoPrato());

					if (this.resultConfirm == JOptionPane.YES_OPTION) {
						acertei();
						break;
					}
				} else {
					acertei();
					break;
				}
			}

		}

		this.pratoPreferido(contador);

	}

	private void confirmaPrato(String subPrato) {
		this.resultConfirm = JOptionPane.showConfirmDialog(this,
				String.format(GourmetConstantes.MSG_PRATO_PENSOU, subPrato), GourmetConstantes.CONFIRMACAO,
				JOptionPane.YES_NO_OPTION);
	}

	private void acertei() {
		JOptionPane.showMessageDialog(this, GourmetConstantes.MSG_ACERTEI, "Acertei", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que armazena o prato preferido
	 * 
	 * @author andre.lermen
	 *
	 */
	private void pratoPreferido(int contador) {
		if (this.resultConfirm == 1) {

			String entradaPrato = JOptionPane.showInputDialog(this, GourmetConstantes.MSG_QUAL_PRATO,
					GourmetConstantes.PRATO, JOptionPane.QUESTION_MESSAGE);

			String tipoPrato = JOptionPane.showInputDialog(this,
					String.format(GourmetConstantes.MSG_TIPO_PRATO, entradaPrato, this.obterPratoAnterior(contador)),
					GourmetConstantes.CARACTERISTICA_PRATO, JOptionPane.QUESTION_MESSAGE);

			this.pratos.add(1, new Prato(entradaPrato, tipoPrato));
		}
	}

	private String obterPratoAnterior(int contador) {
		Prato pratoAnterior = this.pratos.get(contador - 1);
		return pratoAnterior.getPrato();
	}

	private void initComponents() {
		setTitle(GourmetConstantes.MSG_TITULO);
		setVisible(true);

		JLabel label = new JLabel(GourmetConstantes.MSG_DIALOGO_GOSTO);
		label.setFont(new Font("Tahoma", 1, 11));
		label.setText(GourmetConstantes.MSG_DIALOGO_GOSTO);

		JButton button = new JButton(GourmetConstantes.OK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarMotor();
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(GourmetConstantes.MSG_TITULO);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(108, 108, 108).addComponent(label))
						.addGroup(layout.createSequentialGroup().addGap(145, 145, 145).addComponent(button,
								javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(123, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(64, Short.MAX_VALUE).addComponent(label)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(button)
						.addGap(19, 19, 19)));

		pack();
		setLocationRelativeTo(null);
	}
}
