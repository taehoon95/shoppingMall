package shoppingMall.ui.panel;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class imgPanel extends JPanel {

	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	private ImageIcon icon = new ImageIcon(imgPath + "smlie.jpg");
	
	public imgPanel() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblImage = new JLabel("");
		lblImage.setIcon(icon);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblImage);
	}

}
