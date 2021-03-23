package shoppingMall.ui.cuspanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import shoppingMall.dto.Product;
import java.awt.Font;

public class productInfoDetailPanel extends JPanel implements ActionListener {
	
	private JTextField tfProdcode;
	private JTextField tfProdname;
	private JTextField tfProdStock;
	private JPanel pLeft;
	private JPanel pRight;
	private JButton btnUpdate;
	
	// 제품 사진 넣기
	private JLabel lblPic;
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	private File imgFile;
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));

	public productInfoDetailPanel() {

		initialize();
		loadPic(null);
	}
	
	// 제품 사진 넣기
		private void loadPic(String imgFilePath) {
			Image changeImage = null;
			if(imgFilePath == null) {
				ImageIcon icon = new ImageIcon(imgPath + "smlie.jpg");
				changeImage = icon.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH);
			}else {
				ImageIcon changeIcon = new ImageIcon(changeImage);
				lblPic.setIcon(changeIcon);
			}
			ImageIcon changeIcon = new ImageIcon(changeImage);
			lblPic.setIcon(changeIcon);
		}
	
	private void initialize() {
		setBorder(new EmptyBorder(15, 0, 15, 5));
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 2, 0, 15));
		
		pLeft = new JPanel();
		pLeft.setBackground(Color.WHITE);
		add(pLeft);
		
		lblPic = new JLabel();
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		lblPic.setPreferredSize(new Dimension(210, 180));
		pLeft.add(lblPic);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(this);
		btnUpdate.setBackground(Color.GREEN);
		pLeft.add(btnUpdate);
		
		pRight = new JPanel();
		pRight.setBorder(new EmptyBorder(15, 0, 10, 10));
		pRight.setBackground(Color.WHITE);
		add(pRight);
		pRight.setLayout(new GridLayout(0, 2, 0, 30));
		
		JLabel lblProdcode = new JLabel("상품번호");
		pRight.add(lblProdcode);
		lblProdcode.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfProdcode = new JTextField();
		tfProdcode.setFont(new Font("굴림", Font.BOLD, 15));
		pRight.add(tfProdcode);
		tfProdcode.setColumns(10);
		
		JLabel lblProdname = new JLabel("상품이름");
		pRight.add(lblProdname);
		lblProdname.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfProdname = new JTextField();
		tfProdname.setFont(new Font("굴림", Font.BOLD, 15));
		pRight.add(tfProdname);
		tfProdname.setColumns(10);
		
		JLabel lblProdStock = new JLabel("남은 수량");
		pRight.add(lblProdStock);
		lblProdStock.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfProdStock = new JTextField();
		tfProdStock.setFont(new Font("굴림", Font.BOLD, 15));
		pRight.add(tfProdStock);
		tfProdStock.setColumns(10);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUpdate) {
			actionPerformedBtnUpdate(e);
		}
	}
	
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF & PNG image", "jpg","gif","png");
		chooser.setFileFilter(filter);
		int res = chooser.showOpenDialog(null);
		if(res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}else {
			imgFile = chooser.getSelectedFile();
			lblPic.setIcon(new ImageIcon(imgFile.getPath()));
			
		}
	}
	
	public void setItem(Product prod) {
		tfProdcode.setText(prod.getProcode());
		tfProdname.setText(prod.getProname());
		tfProdStock.setText(prod.getStock()+"");
		lblPic.setIcon(new ImageIcon(imgPath + prod.getProdpic()));
	}
	
	public Product getProd() {
		String procode = tfProdcode.getText();
		String proname = tfProdname.getText();
		int stock = Integer.parseInt(tfProdStock.getText());
		String prodpic = lblPic.getName();
		return new Product(procode, proname, stock, prodpic);
	}
	
	public void prohibitionBtn() {
		tfProdcode.setEnabled(false);
		tfProdname.setEnabled(false);
		tfProdStock.setEnabled(false);
	}
}
