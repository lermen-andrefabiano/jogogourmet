package br.com.jogo.gourmet;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import br.com.jogo.gourmet.enuns.GourmetProperties;
import br.com.jogo.gourmet.model.Prato;

/**
 * Classe mestre para iniciar o jogo gourmet
 * 
 * @author andre.lermen
 *
 */
public class GourmetMotor extends JFrame implements Serializable {

	private static final long serialVersionUID = -8331285322591800101L;

	private static final String GOURMET_PROPERTIES = "gourmet.properties";

	private static final String FONT_TAHOMA = "Tahoma";

	private List<Prato> pratoMassa;

	private List<Prato> pratoBolo;

	private int resultConfirm;

	private Properties prop;

	/**
	 * Método que inicia o jogo
	 */
	public void start() {
		this.initConfiguracaoPadrao();

		this.initComponents();

	}

	/**
	 * Método que inicializa os valores de todas as labes do jogo
	 */
	public Properties getProperties() {
		if (this.prop == null) {
			// obtem as lables do arquivo gourmet.properties
			InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(GOURMET_PROPERTIES);

			this.prop = new Properties();

			try {
				this.prop.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return this.prop;

	}

	/**
	 * Método que adiciona as configurações iniciais ao jogo
	 */
	public void initConfiguracaoPadrao() {
		// inicializa os prato de massa
		this.pratoMassa = new ArrayList<>();

		// inicializa os pratos de bolo
		this.pratoBolo = new ArrayList<>();

		// prato de massa
		Prato pratoMassa = new Prato(this.getProperties().getProperty(GourmetProperties.LASANHA.toString()), "");

		// prato de bolo
		Prato pratoBolo = new Prato(this.getProperties().getProperty(GourmetProperties.BOLO_CHOCOLATE.toString()), "");

		this.pratoMassa.add(pratoMassa);
		this.pratoBolo.add(pratoBolo);
	}

	private void executarPratoMassa() {
		this.confirmaPrato(this.getProperties().getProperty(GourmetProperties.PRATO_MASSA.toString()));

		if (this.resultConfirm == JOptionPane.YES_OPTION) {
			this.executarMotorJogo(this.pratoMassa);
		} else {
			this.executarMotorJogo(this.pratoBolo);
		}

	}

	/**
	 * Método responsável por executar o jogo
	 * 
	 */
	private void executarMotorJogo(List<Prato> pratos) {
		int contador;

		for (contador = 0; contador <= pratos.size() - 1; contador++) {

			// recupera o prato
			Prato prato = pratos.get(contador);

			if (prato.getCaracteristica().isEmpty()) {

				String msg = String.format(this.getProperties().getProperty(GourmetProperties.PRATO_PENSOU.toString()),
						prato.getDescricao());

				// confirma o prato informado
				this.confirmaPrato(msg);

				if (this.resultConfirm == JOptionPane.YES_OPTION) {
					this.acertei();
					break;
				}
			} else {
				String caracteristica = String.format(
						this.getProperties().getProperty(GourmetProperties.PRATO_PENSOU.toString()),
						prato.getCaracteristica());
				this.confirmaPrato(caracteristica);

				if (this.resultConfirm == JOptionPane.YES_OPTION) {

					String descricao = String.format(
							this.getProperties().getProperty(GourmetProperties.PRATO_PENSOU.toString()),
							prato.getDescricao());

					this.confirmaPrato(descricao);

					if (this.resultConfirm == JOptionPane.YES_OPTION) {
						this.acertei();
						break;
					}
				}
			}

		}

		this.pratoPreferido(contador, pratos);

	}

	private void confirmaPrato(String msg) {
		this.resultConfirm = JOptionPane.showConfirmDialog(this, msg,
				this.getProperties().getProperty(GourmetProperties.CONFIRMACAO.toString()), JOptionPane.YES_NO_OPTION);
	}

	/**
	 * Método que mostra a mensagem de acerto do prato
	 */
	private void acertei() {
		JOptionPane.showMessageDialog(this, this.getProperties().getProperty(GourmetProperties.ACERTEI_NOVO.toString()),
				this.getProperties().getProperty(GourmetProperties.ACERTEI.toString()),
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método que armazena o prato preferido
	 * 
	 */
	private void pratoPreferido(int contador, List<Prato> pratos) {
		// caso todas as respostas anteriores forem NÂO
		if (this.resultConfirm == 1) {

			// pede qual prato gosta
			String descricaoPrato = JOptionPane.showInputDialog(this,
					this.getProperties().getProperty(GourmetProperties.QUAL_PRATO.toString()),
					this.getProperties().getProperty(GourmetProperties.PRATO.toString()), JOptionPane.QUESTION_MESSAGE);

			// pede qual caracteristica do prato que gosta
			String caracteristicaPrato = JOptionPane.showInputDialog(this,
					String.format(this.getProperties().getProperty(GourmetProperties.TIPO_PRATO.toString()),
							descricaoPrato, this.obterPratoAnterior(contador, pratos)),
					this.getProperties().getProperty(GourmetProperties.CARACTERISTICA_PRATO.toString()),
					JOptionPane.QUESTION_MESSAGE);

			// adiciona o novo prato na lista de pratos
			pratos.add(0, new Prato(descricaoPrato, caracteristicaPrato));
		}
	}

	/**
	 * Método que pega o último prato negado
	 * 
	 * @param contador
	 * @return String
	 */
	private String obterPratoAnterior(int contador, List<Prato> pratos) {
		int index = contador > 0 ? contador - 1 : 0;
		Prato pratoAnterior = pratos.get(index);
		return pratoAnterior.getDescricao();
	}

	/**
	 * Inicia os componentes de tela do jogo
	 * 
	 */
	private void initComponents() {
		setTitle(this.getProperties().getProperty(GourmetProperties.TITULO.toString()));
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JLabel label = new JLabel(this.getProperties().getProperty(GourmetProperties.DIALOGO_GOSTO.toString()));
		label.setFont(new Font(FONT_TAHOMA, 1, 11));

		JButton button = new JButton(this.getProperties().getProperty(GourmetProperties.OK.toString()));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executarPratoMassa();
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup().addContainerGap(64, Short.MAX_VALUE).addComponent(label)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(button).addGap(15, 15,
								15)));

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(108, 108, 108).addComponent(label))
						.addGroup(layout.createSequentialGroup().addGap(145, 145, 145).addComponent(button,
								GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(123, Short.MAX_VALUE));

		getContentPane().setLayout(layout);

		pack();
		setLocationRelativeTo(null);
	}

	public List<Prato> getPratoMassa() {
		return pratoMassa;
	}

	public void setPratoMassa(List<Prato> pratoMassa) {
		this.pratoMassa = pratoMassa;
	}

	public List<Prato> getPratoBolo() {
		return pratoBolo;
	}

	public void setPratoBolo(List<Prato> pratoBolo) {
		this.pratoBolo = pratoBolo;
	}

	public int getResultConfirm() {
		return resultConfirm;
	}

	public void setResultConfirm(int resultConfirm) {
		this.resultConfirm = resultConfirm;
	}

}
