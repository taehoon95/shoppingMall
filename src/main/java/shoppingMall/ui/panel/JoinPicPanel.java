package shoppingMall.ui.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class JoinPicPanel extends JPanel {

	public JoinPicPanel() {

		initialize();
	}
	private void initialize() {
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(120, 150));
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("\uBD88\uB7EC\uC624\uAE30");
		panel.add(btnNewButton, BorderLayout.SOUTH);
	}

}
