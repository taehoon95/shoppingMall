package shoppingMall.ui.frame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.ui.panel.UpdateDetailManagerPanel;

@SuppressWarnings("serial")
public class UpdateDetailManager extends JFrame {

	private JPanel contentPane;

	public UpdateDetailManager() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		UpdateDetailManagerPanel panel = new UpdateDetailManagerPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("수정");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		panel_1.add(btnNewButton_1);
	}
	


}
