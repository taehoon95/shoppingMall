package shoppingMall.dto;

public class Product {
	private String procode;
	private String proname;
	private int proprice;
	private int stock;

	public Product() {}

	public Product(String procode, String proname, int proprice, int stock) {
		this.procode = procode;
		this.proname = proname;
		this.proprice = proprice;
		this.stock = stock;
	}

	public Product(String procode) {
		this.procode = procode;
	}

	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public int getProprice() {
		return proprice;
	}

	public void setProprice(int proprice) {
		this.proprice = proprice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s", procode, proname, proprice == 0 ? "" : proprice,
				stock == 0 ? "" : stock);
	}

}
