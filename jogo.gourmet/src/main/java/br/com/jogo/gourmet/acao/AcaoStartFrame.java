package br.com.jogo.gourmet.acao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.jogo.gourmet.GourmetConstantes;

public class AcaoStartFrame extends AcaoGourmet{

	private static final long serialVersionUID = -9116583548297657743L;
	
	@Override
	public void executar() {
		this.initFrame();		
	}
	
	/**
	 * Método que cria e inicializa as configurações do game
	 * 
	 * @author andre.lermen
	 *
	 */
	private void initFrame() {
		super.frame = new JFrame(GourmetConstantes.MSG_TITULO);
		super.frame.setSize(250, 180);
		super.frame.setVisible(true);
		super.frame.setLocationRelativeTo(null);

		JLabel label = new JLabel(GourmetConstantes.MSG_DIALOGO_GOSTO);

		JButton button = new JButton(GourmetConstantes.OK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proximo();
			}
		});

		GridBagConstraints gridBagConstraints = new GridBagConstraints();

		JPanel panelLabel = this.obterPanelLabel();

		JPanel panelButton = this.obterPanelButton();

		panelLabel.add(label, gridBagConstraints);
		panelButton.add(button, gridBagConstraints);

		super.frame.getContentPane().add(panelLabel);
		super.frame.getContentPane().add(panelButton);
		super.frame.setVisible(true);
		super.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private void proximo(){
		super.executar();
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
