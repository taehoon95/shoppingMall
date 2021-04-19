package shoppingMall.dto;

public class Product {
	private String procode;
	private String proname;
	private int proprice;
	private int stock;
	private int salePrice;
	private String prodpic;
	
	
	public Product() {}

	
	
	public Product(String procode, String proname, int stock, String prodpic) {
		this.procode = procode;
		this.proname = proname;
		this.stock = stock;
		this.prodpic = prodpic;
	}



	public Product(String procode, String proname, int stock) {
		this.procode = procode;
		this.proname = proname;
		this.stock = stock;
	}



	public Product(String procode, String proname, int proprice, int stock, int salePrice, String prodpic) {
		this.procode = procode;
		this.proname = proname;
		this.proprice = proprice;
		this.stock = stock;
		this.salePrice = salePrice;
		this.prodpic = prodpic;
	}

	public Product(String procode, String proname, int proprice, int stock, int salePrice) {
		this.procode = procode;
		this.proname = proname;
		this.proprice = proprice;
		this.stock = stock;
		this.salePrice = salePrice;
	}
	

	public Product(String procode, String proname, int proprice, int stock, String prodpic) {
		this.procode = procode;
		this.proname = proname;
		this.proprice = proprice;
		this.stock = stock;
		this.prodpic = prodpic;
	}



	public String getProdpic() {
		return prodpic;
	}

	public void setProdpic(String prodpic) {
		this.prodpic = prodpic;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public Product(String procode) {
		this.procode = procode;
	}

	public int getSalePrice() {
		return salePrice;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((procode == null) ? 0 : procode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (procode == null) {
			if (other.procode != null)
				return false;
		} else if (!procode.equals(other.procode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", procode, proname, proprice == 0 ? "" : proprice,
				stock == 0 ? "" : stock, prodpic == null ? "" : prodpic);
	}

}
