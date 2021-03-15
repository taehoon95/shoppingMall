package shoppingMall.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shoppingMall.dto.Sale;
import shoppingMall.exception.InvaildCheckException;
import shoppingMall.service.saleMainService;
import shoppingMall.ui.panel.MainBottomPanel;
import shoppingMall.ui.panel.MainMidPanel;
import shoppingMall.ui.panel.MainTopPanel;

public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private saleMainService service;
	private MainTopPanel pTop;
	private MainMidPanel pMid;
	private MainBottomPanel pBottom;
	private List<Sale> list;
	
	public MainFrame() {
		service = new saleMainService();
		initialize();
	}
	private void initialize() {
		setTitle("메인화면");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 777, 466);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pTop = new MainTopPanel();
		pTop.getBtnSearch().addActionListener(this);
		contentPane.add(pTop, BorderLayout.NORTH);
		
		pMid = new MainMidPanel();
		pMid.loadData();
		contentPane.add(pMid, BorderLayout.CENTER);
		
		pBottom = new MainBottomPanel();
		contentPane.add(pBottom, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == pTop.getBtnSearch()) {
				pTopBtnSelectActionPerformed(e);
			}	
		}catch(InvaildCheckException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	}
	protected void pTopBtnSelectActionPerformed(ActionEvent e) {
		Sale searchByDate = searchDate();
		list = service.selectMainByDate(searchByDate);
		pMid.selectList(list);
		pTop.tfClear();

	}
	private Sale searchDate() {
		SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Date searchDate = pTop.getJDate().getDate();
		String date = searchDateFormat.format(searchDate);
		Sale searchByDate = new Sale(date);
		return searchByDate;
	}
}
