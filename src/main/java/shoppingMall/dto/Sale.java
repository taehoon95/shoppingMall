package shoppingMall.dto;

public class Sale {
	private int orderno;
	private String date;
	private Customer cusno;
	private Product procode;
	private int saleamount;
	private int sales;
	private int profit;

	
	
	public Sale(int orderno, String date, Customer cusno, Product procode, int saleamount, int sales, int profit) {
		this.orderno = orderno;
		this.date = date;
		this.cusno = cusno;
		this.procode = procode;
		this.saleamount = saleamount;
		this.sales = sales;
		this.profit = profit;
	}

	public Sale(String date, Customer cusno, Product procode, int saleamount, int sales, int profit) {
		this.date = date;
		this.cusno = cusno;
		this.procode = procode;
		this.saleamount = saleamount;
		this.sales = sales;
		this.profit = profit;
	}

	public Sale(String date, Customer cusno, Product procode) {
		this.date = date;
		this.cusno = cusno;
		this.procode = procode;
	}

	public Sale(String date, Customer cusno, Product procode, int saleamount) {
		this.date = date;
		this.cusno = cusno;
		this.procode = procode;
		this.saleamount = saleamount;
	}

	public Sale(String date, int orderno, Customer cusno, Product procode, int saleamount) {
		this.date = date;
		this.orderno = orderno;
		this.cusno = cusno;
		this.procode = procode;
		this.saleamount = saleamount;
	}
	
	public Sale(String date) { // 날짜별 조회 메인문에서 사용
		this.date = date;
	}
	
	public Sale() {
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Customer getCusno() {
		return cusno;
	}

	public void setCusno(Customer cusno) {
		this.cusno = cusno;
	}

	public Product getProcode() {
		return procode;
	}

	public void setProcode(Product procode) {
		this.procode = procode;
	}

	public int getSaleamount() {
		return saleamount;
	}

	public void setSaleamount(int saleamount) {
		this.saleamount = saleamount;
	}

	@Override
	public String toString() {// main
		return String.format("%s %s %s %s %s %s %s", date, cusno.getCusno(), cusno.getCusname(), cusno.getCallno(),
				procode.getProcode(), saleamount, sales);
	}

	public String toString2() {// product
		return String.format("%4s %4s %4s %4s %4s %5s %5s", cusno.getCusno(), date, procode.getProcode(),
				procode.getProname(), saleamount, procode.getProprice(), sales, profit);
	}

	public String toString3() {// detail
		return String.format("%4s %4s %4s %4s %4s %5s %5s %s", date, procode.getProcode(), procode.getProname(),
				cusno.getCusname(), saleamount, procode.getProprice(), sales, profit);
	}
}
